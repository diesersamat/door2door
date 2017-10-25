package me.sgayazov.door2door.di.module

import dagger.Module
import dagger.Provides
import me.sgayazov.door2door.activity.MapView
import me.sgayazov.door2door.di.PerActivity

@Module
class MapActivityModule(private val mapView: MapView) {

    @Provides
    @PerActivity
    fun provideView(): MapView {
        return mapView
    }
}