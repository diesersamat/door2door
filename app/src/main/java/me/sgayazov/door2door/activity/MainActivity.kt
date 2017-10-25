package me.sgayazov.door2door.activity

import android.os.Bundle
import me.sgayazov.door2door.Application
import me.sgayazov.door2door.R
import me.sgayazov.door2door.di.component.DaggerMainActivityComponent
import me.sgayazov.door2door.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData()
    }

    override fun inject() {
        DaggerMainActivityComponent
                .builder()
                .appComponent((application as Application).appComponent())
                .build()
                .inject(this)
    }
}