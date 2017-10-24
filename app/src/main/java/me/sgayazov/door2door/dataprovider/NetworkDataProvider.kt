package me.sgayazov.door2door.dataprovider

import io.reactivex.Observable
import me.sgayazov.door2door.domain.Data
import me.sgayazov.door2door.networking.RetrofitService
import retrofit2.Retrofit

const private val BASE_URL = "https://raw.githubusercontent.com/door2door-io/transit-app-task/"

class NetworkDataProvider {

    private val service = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitService::class.java)

    fun getAllData(): Observable<Data> {
        return service.getAllData()
    }
}