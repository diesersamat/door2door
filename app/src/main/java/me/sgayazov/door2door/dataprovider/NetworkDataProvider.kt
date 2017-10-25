package me.sgayazov.door2door.dataprovider

import io.reactivex.Observable
import me.sgayazov.door2door.domain.Data
import me.sgayazov.door2door.networking.RetrofitService
import javax.inject.Inject

class NetworkDataProvider @Inject constructor() {

    @Inject
    lateinit var apiService: RetrofitService

    fun getAllData(): Observable<Data> {
        return apiService.getAllData()
    }
}