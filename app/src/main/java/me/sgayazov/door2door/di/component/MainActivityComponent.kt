package me.sgayazov.door2door.di.component

import dagger.Component
import me.sgayazov.door2door.activity.MainView
import me.sgayazov.door2door.di.PerActivity
import me.sgayazov.door2door.di.module.MainActivityModule

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(mainView: MainView)
}