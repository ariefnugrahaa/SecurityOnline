package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.myreport


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.arief.securityonline.R
import com.example.arief.securityonline.adapter.RVMyReport
import com.example.arief.securityonline.extension.makeGone
import com.example.arief.securityonline.extension.makeVisibile
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.DetailReportActivity
import com.example.arief.securityonline.extension.showToastErrorFromServer
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import kotlinx.android.synthetic.main.fragment_my_report.*
import org.jetbrains.anko.support.v4.act


class MyReportFragment : Fragment(), BaseInterface.IMyReport {

    private val presenter by lazy {
        GetMyReportPresenter(this)
    }
    private val sharedPreference by lazy { SharedPrefManager.getInstance(act.applicationContext) }
    private var loadData = mutableListOf<HomeDataModel.ResponseData>()
    private var filteredData = mutableListOf<HomeDataModel.ResponseData>()
    private lateinit var rvMyReport: RVMyReport

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lottie_myreport.setAnimation("notfound.json")

        val dorefresh = view.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout

        dorefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)

        dorefresh.setOnRefreshListener {
            presenter.getMyReport(act.applicationContext, sharedPreference.getValueUsername("Username").toString())
            dorefresh.isRefreshing = false
        }


        presenter.getMyReport(act.applicationContext, sharedPreference.getValueUsername("Username").toString())

        rvMyReport = RVMyReport { position ->
            val intent = Intent(context, DetailReportActivity::class.java).apply {
                putExtra(DetailReportActivity.INTENT_EXTRA, filteredData[position].iDLaporan)
                putExtra(DetailReportActivity.INTENT_STATUS_REPORT, filteredData[position].status)
            }
            startActivity(intent)
        }

        //Searching
        et_search_myreport.addTextChangedListener(object : TextWatcher{
            @SuppressLint("DefaultLocale")
            override fun afterTextChanged(p0: Editable?) {

                try {
                    val textToFilter = et_search_myreport.text.toString().toLowerCase().trim()
                    filteredData = loadData.filter { data -> data.motif.toLowerCase().contains(textToFilter)
                    }.toMutableList()

                    if (textToFilter.isNotEmpty()) {
                        rvMyReport.setData(filteredData)

                    } else {
                        getListAdapter()!!.setData(filteredData)
                    }
                } catch (e: Exception){ }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)  = Unit

        })

    }

    private fun getListAdapter(): RVMyReport? = rv_myreport.adapter as? RVMyReport?

    override fun onDataCompleteMyReport(q: HomeDataModel) {

        try {

            loadData = q.responseData.toMutableList()

            filteredData = loadData

            if (loadData.isNullOrEmpty()) {
                lottie_myreport.makeVisibile()
                lottie_myreport.setAnimation("notfound.json")
                lottie_myreport.playAnimation()
                cv_myreport_searchview.makeGone()
            } else {
                rv_myreport.apply {
                    rvMyReport.setData(loadData)
                    layoutManager = LinearLayoutManager(context)
                    adapter = rvMyReport
                }
                lottie_myreport.makeGone()
                cv_myreport_searchview.makeVisibile()
            }
        }catch (e: Exception){ }

    }

    override fun onErrorMyReport(t: Throwable) {
        lottie_myreport.makeVisibile()
        lottie_myreport.setAnimation("notfound.json")
        lottie_myreport.playAnimation()
        act.showToastErrorFromServer("Error From Server")
    }
}
