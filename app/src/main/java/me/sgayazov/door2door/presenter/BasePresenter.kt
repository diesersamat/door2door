package me.sgayazov.door2door.presenter

import me.sgayazov.door2door.dataprovider.Interactor

abstract class BasePresenter {
    var interactor: Interactor = Interactor()
}