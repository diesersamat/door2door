package me.sgayazov.door2door.di.component

import dagger.Component
import me.sgayazov.door2door.Application
import me.sgayazov.door2door.di.module.AppModule
import me.sgayazov.door2door.presenter.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Application)

    fun getMainPresenter(): MainPresenter
}