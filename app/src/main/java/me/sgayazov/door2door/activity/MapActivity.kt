package me.sgayazov.door2door.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED
import android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import me.sgayazov.door2door.R
import me.sgayazov.door2door.domain.MapPoint
import me.sgayazov.door2door.presenter.MapPresenter


private const val WIDTH_LINE = 5f
private const val ZOOM = 12f

class MapActivity : AppCompatActivity(), OnMapReadyCallback, MapView {

    private var presenter: MapPresenter = MapPresenter(this)

    private var polyline: Polyline? = null
    private lateinit var map: GoogleMap
    private lateinit var clusterManager: ClusterManager<ClusterItem>
    private lateinit var bottomSheet: BottomSheetBehavior<LinearLayout>
    private lateinit var provider: TextView
    private lateinit var type: TextView
    private lateinit var name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        bottomSheet = BottomSheetBehavior.from(findViewById<LinearLayout>(R.id.bottom_sheet))
        bottomSheet.state = STATE_COLLAPSED
        bottomSheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == STATE_COLLAPSED) {
                    polyline?.remove()
                    polyline = null
                }
            }
        })
        type = findViewById<TextView>(R.id.type)
        provider = findViewById<TextView>(R.id.provider)
        name = findViewById<TextView>(R.id.name)
        findViewById<View>(R.id.open_application).setOnClickListener { tryToOpenApp() }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        clusterManager = ClusterManager<ClusterItem>(this, map)
        clusterManager.setOnClusterClickListener { presenter.onClusterClick(it) }
        clusterManager.setOnClusterItemClickListener { presenter.onClusterClick(it as MapPoint) }
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
        map.setOnInfoWindowClickListener(clusterManager)
        presenter.loadData()
    }

    override fun onDataReceived(data: List<MapPoint>) {
        clusterManager.clearItems()
        clusterManager.addItems(data)
        clusterManager.cluster()
    }

    override fun focusOnPoint(lat: Double, lng: Double) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), ZOOM))
    }

    override fun drawPolyline(polylineString: String, color: String) {
        polyline?.remove()
        val polyLineOptions = PolylineOptions()
        polyLineOptions.addAll(PolyUtil.decode(polylineString))
        polyLineOptions.width(WIDTH_LINE)
        polyLineOptions.color(Color.parseColor(color))
        polyline = map.addPolyline(polyLineOptions)
    }

    override fun showDetailedInfo(name: String, provider: String, type: String) {
        //todo show all detailed info
        bottomSheet.state = STATE_EXPANDED
        this.provider.text = provider
        this.type.text = type
        this.name.text = name
    }


    private fun tryToOpenApp() {
        presenter.tryToOpenApp()
    }


    override fun openApp(packageName: String) {
        var intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent == null) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        }
        startActivity(intent)
    }
}

interface MapView {
    fun onDataReceived(data: List<MapPoint>)
    fun focusOnPoint(lat: Double, lng: Double)
    fun drawPolyline(polyline: String, color: String)
    fun showDetailedInfo(name: String, provider: String, type: String)
    fun openApp(packageName: String)
}
