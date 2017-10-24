package me.sgayazov.door2door.di

import dagger.Module
import dagger.Provides
import me.sgayazov.door2door.Application
import javax.inject.Singleton

@Module
@Singleton
class AppModule(private val app: Application) {
    @Provides
    fun provideApplication() = app
}