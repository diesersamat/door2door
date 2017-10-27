package me.sgayazov.door2door.presenter

import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterItem
import me.sgayazov.door2door.activity.MapView
import me.sgayazov.door2door.domain.Data
import me.sgayazov.door2door.domain.MapPoint
import me.sgayazov.door2door.domain.ProviderAttributes

class MapPresenter constructor(private val view: MapView) : BasePresenter() {

    private val list: MutableList<MapPoint> = mutableListOf()
    private var selectedPoint: MapPoint? = null
    private var providerAttributes: ProviderAttributes? = null

    fun loadData() {
        interactor.getAllData().subscribe({ t1: Data?, t2: Throwable? ->
            //todo show errors, view.onError()
            t2?.printStackTrace()
            t1?.let { onDataReceived(t1) }
        })
    }

    private fun onDataReceived(data: Data) {
        list.clear()

        //todo camelcase namings with @Json name annotation
        providerAttributes = data.provider_attributes

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
        //todo open scrollable details bottom sheet (like view.showDetailedInfo)
        return true
    }

    fun onClusterClick(cluster: MapPoint): Boolean {
        selectedPoint = cluster
        view.drawPolyline(cluster.segment.polyline, cluster.segment.color)
        view.showDetailedInfo(cluster.stop.name, cluster.route.provider, cluster.route.type)
        return true
    }

    fun tryToOpenApp() {
        val provider = when (selectedPoint?.route?.provider) {
            "vbb" -> providerAttributes?.vbb
            "drivenow" -> providerAttributes?.drivenow
            "car2go" -> providerAttributes?.car2go
            "google" -> providerAttributes?.google
            "nextbike" -> providerAttributes?.nextbike
            "callabike" -> providerAttributes?.callabike
            else -> null
        }
        provider?.android_package_name?.let {
            view.openApp(it)
        }
    }
}