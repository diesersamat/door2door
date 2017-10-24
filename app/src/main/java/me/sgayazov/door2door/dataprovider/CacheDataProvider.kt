package me.sgayazov.door2door.dataprovider

import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableEmpty
import me.sgayazov.door2door.domain.Data

class CacheDataProvider {
    fun getAllData(): Observable<Data> {
        //todo read from database
        return ObservableEmpty.empty()
    }

    fun saveAllData(data: Data) {
        //todo save to database
    }

}