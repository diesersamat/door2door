package me.sgayazov.door2door.activity

import android.content.Intent
import android.os.Bundle
import me.sgayazov.door2door.R
import me.sgayazov.door2door.domain.Data
import me.sgayazov.door2door.presenter.MainPresenter

class MainActivity : BaseActivity(), MainView {
    override fun onDataReceived(t1: Data) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        startActivity(Intent(this, MapActivity::class.java))
        presenter.loadData()
    }
}


interface MainView {
    fun onDataReceived(t1: Data)
}
