package net.swimmi.androidlibrary.ui

import android.view.View
import net.swimmi.androidlibrary.R
import net.swimmi.androidlibrary.base.BaseFragment

class Fragment1: BaseFragment() {

    override fun getLayoutView(): View {
        return View.inflate(context, R.layout.fragment_1, null)
    }

    override fun initView() {
    }

}