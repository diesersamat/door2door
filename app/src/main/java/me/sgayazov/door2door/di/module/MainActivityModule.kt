package me.sgayazov.door2door.di.module

import dagger.Module
import dagger.Provides
import me.sgayazov.door2door.activity.MainView
import me.sgayazov.door2door.di.PerActivity

@Module
class MainActivityModule(val mainView: MainView) {
    @Provides
    @PerActivity
    fun provideView(): MainView {
        return mainView
    }
}