package com.example.arief.securityonline.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.arief.securityonline.R
import com.example.arief.securityonline.extension.openingDialog
import com.example.arief.securityonline.network.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var prevMenuItem: MenuItem? = null

    private val sharedPreference by lazy {
        SharedPrefManager.getInstance(this)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openingDialog(this)

        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewpager.currentItem = 0
                R.id.navigation_dashboard -> viewpager.currentItem = 1
                R.id.notifcation -> viewpager.currentItem = 2
                R.id.userprofile -> viewpager.currentItem = 10
            }
            false
        }

        viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    print("a")
                } else {
                    nav_view.menu.getItem(0).isChecked = false
                }
                nav_view.menu.getItem(position).isChecked = true
                prevMenuItem = nav_view.menu.getItem(0)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        setupViewPager(viewpager)
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(
            supportFragmentManager)
        adapter.addFragment(HomeFragment())
        adapter.addFragment(ReportFragment())
        adapter.addFragment(NotificationFragment())
        adapter.addFragment(UserFragment())



        viewPager.adapter = adapter
    }

    @Suppress("DEPRECATION")
    inner class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }
    }

    override fun onStart() {
        super.onStart()
        try {
            if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        } catch (e: Exception) { }


    }
}

