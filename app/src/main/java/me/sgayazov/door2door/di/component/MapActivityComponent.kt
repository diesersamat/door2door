package me.sgayazov.door2door.di.component

import dagger.Component
import me.sgayazov.door2door.activity.MapView
import me.sgayazov.door2door.di.PerActivity
import me.sgayazov.door2door.di.module.MapActivityModule

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MapActivityModule::class))
interface MapActivityComponent {
    fun inject(mapView: MapView)
}
