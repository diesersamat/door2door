package me.sgayazov.door2door.presenter

import me.sgayazov.door2door.activity.MainView
import me.sgayazov.door2door.domain.Data
import javax.inject.Inject

class MainPresenter @Inject constructor(val mainView: MainView) : BasePresenter() {

    fun loadData() {
        interactor.getAllData().subscribe({ t1: Data?, t2: Throwable? ->
            t2?.printStackTrace()
        })
    }
}