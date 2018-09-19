package net.swimmi.androidlibrary.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        initView()
    }

    abstract fun setView()

    abstract fun initView()
}