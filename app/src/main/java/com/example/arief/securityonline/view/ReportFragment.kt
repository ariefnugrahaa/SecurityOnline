package com.example.arief.securityonline.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.arief.securityonline.R
import kotlinx.android.synthetic.main.fragment_report.*
import org.jetbrains.anko.support.v4.act

@Suppress("DEPRECATION")
class ReportFragment : Fragment() {

//    lateinit var rvAdapter:RVItem

    lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val adapter = MyViewPagerAdapterr(act.supportFragmentManager)
        adapter.addFragment(MyReportFragment(), "My Report")
        adapter.addFragment(LatestReportFragment(), "All Report")
        viewPagerr.adapter = adapter
        tabss.setupWithViewPager(viewPagerr)


//        searchview.queryHint = Html.fromHtml("<font color = #ffffff>" + resources.getString(R.string.searchview) + "</font>")

//        rvAdapter = RVItem { position -> null }
//
//        rv_laporan.apply {
//            rvAdapter.setData(Constantss.DataBab())
//            layoutManager = LinearLayoutManager(context)
//            adapter = rvAdapter
//        }
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