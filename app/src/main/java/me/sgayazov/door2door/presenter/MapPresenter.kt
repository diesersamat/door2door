package me.sgayazov.door2door.presenter

import me.sgayazov.door2door.activity.MapView
import me.sgayazov.door2door.domain.Data

class MapPresenter constructor(val view: MapView) : BasePresenter() {

    fun loadData() {
        interactor.getAllData().subscribe({ t1: Data?, t2: Throwable? ->
            t2?.printStackTrace()
            t1?.let { view.onDataReceived(t1) }
        })
    }
}