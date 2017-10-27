package me.sgayazov.door2door.activity

import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import me.sgayazov.door2door.R
import me.sgayazov.door2door.domain.Data
import me.sgayazov.door2door.presenter.MapPresenter

class MapActivity : BaseActivity(), OnMapReadyCallback, MapView {

    var presenter: MapPresenter = MapPresenter(this)

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        presenter.loadData()
    }

    override fun onDataReceived(t1: Data) {
        t1.routes.forEach {
            it.segments.forEach {
                it.stops.forEach {
                    map.addMarker(MarkerOptions()
                            .position(LatLng(it.lat, it.lng))
                            .title(it.name))
                }
            }
        }
    }
}

interface MapView {
    fun onDataReceived(t1: Data)
}
