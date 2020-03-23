package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.arief.securityonline.R
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.allreport.AllReportFragment
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.myreport.MyReportFragment
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.questreport.QuestReportFragment
import kotlinx.android.synthetic.main.fragment_report.*
import org.jetbrains.anko.support.v4.act

@Suppress("DEPRECATION")
class BaseReportFragment : Fragment() {

    private val sharedPreference by lazy {
        SharedPrefManager.getInstance(act.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val getRole = sharedPreference.getValueRole("Role").toString()
        val adapter = MyViewPagerAdapterr(act.supportFragmentManager)

        if (getRole == "M" || getRole == "AM"){

            adapter.addFragment(
                MyReportFragment(), "My Report")
            adapter.addFragment(
                AllReportFragment(), "All Report")
            adapter.addFragment(
                QuestReportFragment(), "Tugas Mu")

        } else {

            adapter.addFragment(
                MyReportFragment(), "My Report")
            adapter.addFragment(
                QuestReportFragment(), "Tugas Mu")
        }

        viewPagerr.adapter = adapter
        tabss.setupWithViewPager(viewPagerr)

    }

    inner class MyViewPagerAdapterr(manager: FragmentManager): FragmentPagerAdapter(manager){
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title:String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}