package me.sgayazov.door2door

import android.app.Application
import me.sgayazov.door2door.di.component.AppComponent
import me.sgayazov.door2door.di.component.DaggerAppComponent
import me.sgayazov.door2door.di.module.AppModule

const private val BASE_URL = "https://raw.githubusercontent.com/door2door-io/transit-app-task/"

class Application : Application() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this, BASE_URL))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    fun appComponent(): AppComponent = appComponent
}