package me.sgayazov.door2door.di

import dagger.Component
import me.sgayazov.door2door.Application
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Application)
}