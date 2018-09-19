package net.swimmi.androidlibrary.ui

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.swimmi.androidlibrary.R
import net.swimmi.androidlibrary.adapter.TreasureAdapter
import net.swimmi.androidlibrary.base.BaseActivity
import net.swimmi.androidlibrary.base.BaseFragment
import net.swimmi.androidlibrary.model.Treasure
import net.swimmi.androidlibrary.utils.DataUtil

class MainActivity : BaseActivity() {

    private lateinit var mAdapter: TreasureAdapter
    private lateinit var mList: List<Treasure>

    override fun setView() {
        setContentView(R.layout.activity_main)
    }

    override fun initView() {

        mList = DataUtil(this).getTreasure()
        mAdapter = TreasureAdapter(this, mList, R.layout.item_treasure)
        mAdapter.setItemClickListener(object :TreasureAdapter.OnItemClickListener{
            override fun onItemClick(view: View, positon: Int) {
                val treasure = mList[positon]
                showTreasure(treasure)
            }
        })
        rv_main.adapter = mAdapter
        rv_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ib_collapse.setOnClickListener {
            this.onBackPressed()
        }
    }

    fun showTreasure(treasure: Treasure) {
        val fragment = Fragment.instantiate(applicationContext, "net.swimmi.androidlibrary.ui.Fragment"+treasure.id)
        val transition = supportFragmentManager.beginTransaction()
        transition.setCustomAnimations(R.animator.slide_bottom_enter, R.animator.slide_bottom_exit, R.animator.slide_bottom_enter, R.animator.slide_bottom_exit)
        transition.add(R.id.container, fragment, treasure.id.toString())
        transition.addToBackStack(null)
        transition.commit()
    }

}
