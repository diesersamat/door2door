package me.sgayazov.door2door.networking

import io.reactivex.Observable
import me.sgayazov.door2door.domain.Data
import retrofit2.http.GET

interface RetrofitService {
    @GET("master/data.json")
    fun getAllData(): Observable<Data>
}