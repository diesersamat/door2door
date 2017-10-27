package me.sgayazov.door2door.presenter

import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterItem
import me.sgayazov.door2door.activity.MapView
import me.sgayazov.door2door.domain.Data
import me.sgayazov.door2door.domain.MapPoint

class MapPresenter constructor(private val view: MapView) : BasePresenter() {

    private val list: MutableList<MapPoint> = mutableListOf()

    fun loadData() {
        interactor.getAllData().subscribe({ t1: Data?, t2: Throwable? ->
            t2?.printStackTrace()
            t1?.let { onDataReceived(t1) }
        })
    }

    private fun onDataReceived(data: Data) {
        list.clear()

        var avgLat = 0.0
        var avgLng = 0.0
        var count = 0

        data.routes.forEach { route ->
            route.segments.forEach { segment ->
                segment.stops.forEach { stop ->
                    count++
                    avgLat += stop.lat
                    avgLng += stop.lng
                    list.add(MapPoint(stop, segment, route))
                }
            }
        }
        view.onDataReceived(list)
        view.focusOnPoint(avgLat / count, avgLng / count)
    }

    fun onClusterClick(cluster: Cluster<ClusterItem>): Boolean {
        val listOfCluster: MutableList<MapPoint> = mutableListOf()
        cluster.items.forEach { listOfCluster.add(it as MapPoint) }
        return true
    }

    fun onClusterClick(cluster: MapPoint): Boolean {
        view.drawPolyline(cluster.segment.polyline, cluster.segment.color)
        return true
    }
}