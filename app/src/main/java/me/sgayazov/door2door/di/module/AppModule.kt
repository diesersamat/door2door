package me.sgayazov.door2door.di.module

import dagger.Module
import dagger.Provides
import me.sgayazov.door2door.Application
import me.sgayazov.door2door.networking.RetrofitService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@Singleton
class AppModule(private val app: Application, private val baseUrl: String) {

    @Provides
    fun provideApplication() = app

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}