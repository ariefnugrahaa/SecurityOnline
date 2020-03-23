package com.example.arief.securityonline.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.arief.securityonline.R
import com.example.arief.securityonline.extension.checkPermission
import com.example.arief.securityonline.extension.getCompleteAdress
import com.example.arief.securityonline.extension.isLocationEnabled
import com.example.arief.securityonline.extension.requestLocPermission
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.example.arief.securityonline.network.service.ConnectionReceiver
import com.example.arief.securityonline.presentation.auth.login.LoginActivity
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.writereport.WriteReportFragment
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.BaseReportFragment
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.user.UserFragment
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() , ConnectionReceiver.ConnectReceiverListener  {

    //Local Variable
    var prevMenuItem: MenuItem? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var recentLocation: String? = ""
    private var mSnackBar: Snackbar? = null

    companion object {
        var latitude:Double? = null
        var longitude:Double? = null
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        initView()

    }

    private fun initView(){

        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewpager.currentItem = 0
                R.id.navigation_dashboard -> viewpager.currentItem = 1
                R.id.userprofile -> viewpager.currentItem = 2
            }
            false
        }

        viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                } else {
                    nav_view.menu.getItem(0).isChecked = false
                }
                nav_view.menu.getItem(position).isChecked = true
                prevMenuItem = nav_view.menu.getItem(0)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        setupViewPager(viewpager)

        getLastLocation()


    }

    private fun requestNewLocation() {
        val locRequest = LocationRequest()
        locRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locRequest.interval = 20000
        locRequest.fastestInterval = 3000
        locRequest.numUpdates = 1

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.requestLocationUpdates(locRequest, locationCallback, Looper.myLooper())

    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation: Location = locationResult.lastLocation

            recentLocation = getCompleteAdress(this@MainActivity, lastLocation.latitude, lastLocation.longitude)

        }
    }

    private fun getLastLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocation()
                    } else {
                        latitude = location.latitude
                        longitude = location.longitude
                    }
                }
            } else {
                toast("Turn On GPS")
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestLocPermission(this)
        }
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(
            supportFragmentManager)
        adapter.addFragment(
            WriteReportFragment()
        )
        adapter.addFragment(
            BaseReportFragment()
        )
        adapter.addFragment(
            UserFragment()
        )
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
        try {
            if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        } catch (e: Exception) { }
        super.onStart()
    }

    @SuppressLint("WrongConstant")
    private fun showError(isConnected: Boolean) {
        if (!isConnected) {

            val messageToUser = "You are offline."

            mSnackBar = Snackbar.make(findViewById(R.id.container), messageToUser, Snackbar.LENGTH_LONG)
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) { }

    override fun onResume() {
        super.onResume()
        ConnectionReceiver.connectReceiverListener = this
    }

    override fun onBackPressed() {
        finish()
    }
}

