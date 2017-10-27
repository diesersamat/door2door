package me.sgayazov.door2door.dataprovider

import io.reactivex.Observable
import me.sgayazov.door2door.domain.Data
import me.sgayazov.door2door.networking.RetrofitService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const private val BASE_URL = "https://raw.githubusercontent.com/door2door-io/transit-app-task/"

class NetworkDataProvider {

    var apiService: RetrofitService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(RetrofitService::class.java)

    fun getAllData(): Observable<Data> {
        return apiService.getAllData()
    }
}