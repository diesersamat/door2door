package me.sgayazov.door2door.presenter

import me.sgayazov.door2door.dataprovider.Interactor
import javax.inject.Inject

abstract class BasePresenter {
    @Inject
    lateinit var interactor: Interactor
}