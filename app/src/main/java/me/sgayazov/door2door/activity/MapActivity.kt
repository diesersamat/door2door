package me.sgayazov.door2door.activity

import android.graphics.Color
import android.os.Bundle
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


class MapActivity : BaseActivity(), OnMapReadyCallback, MapView {

    private var presenter: MapPresenter = MapPresenter(this)

    private var polyline: Polyline? = null
    private lateinit var map: GoogleMap
    private lateinit var clusterManager: ClusterManager<ClusterItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 12f))
    }

    override fun drawPolyline(polylineString: String, color: String) {
        polyline?.remove()
        val polyLineOptions = PolylineOptions()
        polyLineOptions.addAll(PolyUtil.decode(polylineString))
        polyLineOptions.width(5f)
        polyLineOptions.color(Color.parseColor(color))
        polyline = map.addPolyline(polyLineOptions)
    }
}

interface MapView {
    fun onDataReceived(data: List<MapPoint>)
    fun focusOnPoint(lat: Double, lng: Double)
    fun drawPolyline(polyline: String, color: String)
}
