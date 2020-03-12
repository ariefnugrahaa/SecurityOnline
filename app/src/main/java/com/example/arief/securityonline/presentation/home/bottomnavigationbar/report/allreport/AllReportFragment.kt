package com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.allreport


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.arief.securityonline.R
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.adapter.RVLatestReport
import com.example.arief.securityonline.extension.makeGone
import com.example.arief.securityonline.extension.makeVisibile
import com.example.arief.securityonline.network.presenter.GetListReportPresenter
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.detailreport.DetailReportActivity
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.detailreport.DetailReportActivity.Companion.INTENT_EXTRA
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.detailreport.DetailReportActivity.Companion.INTENT_SENDTINDAKAN_REPORT
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.detailreport.DetailReportActivity.Companion.INTENT_STATUS_REPORT
import com.example.arief.umkpdconline.common.showToastErrorFromServer
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import kotlinx.android.synthetic.main.fragment_all_report.*
import org.jetbrains.anko.support.v4.act
import java.lang.Exception


class AllReportFragment : Fragment(), BaseInterface.IListReportLatest {

    private val presenter by lazy { GetListReportPresenter(this) }
    private lateinit var rvLatest:RVLatestReport
    private var loadData = mutableListOf<HomeDataModel.ResponseData>()
    private var filteredData = mutableListOf<HomeDataModel.ResponseData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lottie_allreport.setAnimation("notfound.json")

        swipe_refresh_latestreport.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light)

        swipe_refresh_latestreport.setOnRefreshListener {
            presenter.getListReport(act.applicationContext)
            rvLatest.setData(filteredData)
            rvLatest.notifyDataSetChanged()
            swipe_refresh_latestreport.isRefreshing = false
        }

        rvLatest = RVLatestReport { position ->
            val intent = Intent(context, DetailReportActivity::class.java).apply {
                putExtra(INTENT_EXTRA, filteredData[position].iDLaporan)
                putExtra(INTENT_STATUS_REPORT, filteredData[position].status)
                putExtra(INTENT_SENDTINDAKAN_REPORT, filteredData[position].SendTindakan)
            }
            startActivity(intent)
            act.finish()

        }
        presenter.getListReport(act.applicationContext)

        et_search_allreport.addTextChangedListener(object : TextWatcher{

            @SuppressLint("DefaultLocale")
            override fun afterTextChanged(p0: Editable?) {

                try {
                    val textToFilter = et_search_allreport.text.toString().toLowerCase().trim()
                    filteredData = loadData.filter { data -> data.tindakan.toLowerCase().contains(textToFilter) }.toMutableList()

                    if (textToFilter.isNotEmpty()) {
                        rvLatest.setData(filteredData)
                    } else {
                        getListAdapter()?.setData(filteredData)
                    }
                } catch (e: Exception){ }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })
    }


    private fun getListAdapter(): RVLatestReport? = rv_all_report.adapter as? RVLatestReport?

    override fun onDataCompleteListReport(q: HomeDataModel) {

        try {

            loadData = q.responseData.toMutableList()

            filteredData = loadData

            if (loadData.isNullOrEmpty()){
                lottie_allreport.makeVisibile()
                lottie_allreport.setAnimation("notfound.json")
                lottie_allreport.playAnimation()
                cv_allreport_searchview.makeGone()

            } else {
                rv_all_report.apply {
                    rvLatest.setData(loadData.toMutableList())
                    layoutManager = LinearLayoutManager(context)
                    adapter = rvLatest
                }
                lottie_allreport.makeGone()
                cv_allreport_searchview.makeVisibile()
            }

        } catch (e: Exception){ }

    }

    override fun onErrorListReport(t: Throwable) {
        lottie_allreport.makeVisibile()
        act.showToastErrorFromServer("Error From Server")
    }
}
