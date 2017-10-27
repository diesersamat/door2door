package me.sgayazov.door2door.domain

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MapPoint(val stop: Stop, val segment: Segment, val route: Route) : ClusterItem {

    override fun getPosition(): LatLng = LatLng(stop.lat, stop.lng)
}