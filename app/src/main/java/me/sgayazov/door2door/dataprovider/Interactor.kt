package me.sgayazov.door2door.dataprovider

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.sgayazov.door2door.domain.Data

class Interactor constructor() {

    var cacheDataProvider: CacheDataProvider = CacheDataProvider()

    var networkDataProvider: NetworkDataProvider = NetworkDataProvider()

    fun getAllData(): Single<Data> {
        return Observable
                .concat(cacheDataProvider.getAllData(),
                        networkDataProvider.getAllData()
                                .doOnNext({ cacheDataProvider.saveAllData(it) }))
                .filter({ it.isUpToDate() })
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}