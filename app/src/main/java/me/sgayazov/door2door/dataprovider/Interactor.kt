package me.sgayazov.door2door.dataprovider

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.sgayazov.door2door.domain.Data
import javax.inject.Inject

class Interactor @Inject constructor() {

    @Inject
    lateinit var cacheDataProvider: CacheDataProvider

    @Inject
    lateinit var networkDataProvider: NetworkDataProvider

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