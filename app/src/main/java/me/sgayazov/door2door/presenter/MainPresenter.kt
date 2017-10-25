package me.sgayazov.door2door.presenter

import me.sgayazov.door2door.domain.Data
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter() {

    fun loadData() {
        interactor.getAllData().subscribe({ t1: Data?, t2: Throwable? ->
            var i = 0
            i += 1
        })
    }
}