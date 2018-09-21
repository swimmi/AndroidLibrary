package net.swimmi.androidlibrary.ui

import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.swimmi.androidlibrary.R
import net.swimmi.androidlibrary.adapter.TreasureAdapter
import net.swimmi.androidlibrary.base.BaseActivity
import net.swimmi.androidlibrary.model.Treasure
import net.swimmi.androidlibrary.utils.DataUtil
import android.widget.Toast



class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mAdapter: TreasureAdapter
    private lateinit var mList: List<Treasure>

    override fun setView() {
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun initView() {


        mList = DataUtil(this).getTreasure()
        mAdapter = TreasureAdapter(this, mList, R.layout.item_treasure)
        mAdapter.setItemClickListener(object :TreasureAdapter.OnItemClickListener{
            override fun onItemClick(positon: Int) {
                val treasure = mList[positon]
                showTreasure(treasure)
            }
        })
        rv_main.adapter = mAdapter
        rv_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    fun showTreasure(treasure: Treasure) {
        val fragment = Fragment.instantiate(applicationContext, "net.swimmi.androidlibrary.ui.Fragment" + treasure.id)
        val transition = supportFragmentManager.beginTransaction()
        transition.setCustomAnimations(R.animator.slide_bottom_enter, R.animator.slide_bottom_exit)
        transition.add(R.id.container, fragment, treasure.id.toString())
        transition.addToBackStack(null)
        transition.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.collapse -> this.onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {

            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
