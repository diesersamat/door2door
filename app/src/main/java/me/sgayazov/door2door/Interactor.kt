package me.sgayazov.door2door

import io.reactivex.Observable
import io.reactivex.Single
import me.sgayazov.door2door.dataprovider.CacheDataProvider
import me.sgayazov.door2door.dataprovider.NetworkDataProvider
import me.sgayazov.door2door.domain.Data

class Interactor {
    private val cacheDataProvider: CacheDataProvider = CacheDataProvider()
    private val networkDataProvider: NetworkDataProvider = NetworkDataProvider()

    fun getAllData(): Single<Data> {
        return Observable
                .concat(cacheDataProvider.getAllData(),
                        networkDataProvider.getAllData()
                                .doOnNext({ cacheDataProvider.saveAllData(it) }))
                .filter({ it.isUpToDate() })
                .firstOrError()
    }
}