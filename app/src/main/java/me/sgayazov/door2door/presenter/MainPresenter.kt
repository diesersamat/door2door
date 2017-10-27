package me.sgayazov.door2door.presenter

import me.sgayazov.door2door.activity.MainView
import me.sgayazov.door2door.domain.Data

class MainPresenter constructor(val mainView: MainView) : BasePresenter() {

    fun loadData() {
        interactor.getAllData().subscribe({ _: Data?, t2: Throwable? ->
            t2?.printStackTrace()
        })
    }
}