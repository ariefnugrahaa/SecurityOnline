package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.questreport


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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.arief.securityonline.R
import com.example.arief.securityonline.adapter.RVQuestReport
import com.example.arief.securityonline.extension.makeGone
import com.example.arief.securityonline.extension.makeVisibile
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.DetailReportActivity
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.DetailReportActivity.Companion.INTENT_EXTRA
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.DetailReportActivity.Companion.INTENT_STATUS_REPORT
import com.example.arief.securityonline.extension.showToastErrorFromServer
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import kotlinx.android.synthetic.main.fragment_quest_report.*
import org.jetbrains.anko.support.v4.act


class QuestReportFragment : Fragment(), BaseInterface.IGetQuestReport {


    //Init Presenter
    private val presenter by lazy {
        GetQuestReportPresenter(this)
    }
    private val sharedPreferenced by lazy { SharedPrefManager.getInstance(act.applicationContext) }

    //Local Variable
    private var loadData = mutableListOf<HomeDataModel.ResponseData>()
    private var filteredData = mutableListOf<HomeDataModel.ResponseData>()
    lateinit var rvAdapter:RVQuestReport

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quest_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lottie_quest.setAnimation("notfound.json")

        val doRefresh = view.findViewById(R.id.swipe_refresh_quest) as SwipeRefreshLayout

        doRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light)

        doRefresh.setOnRefreshListener {
            presenter.getMyReport(act.applicationContext, sharedPreferenced.getValueUsername("Username").toString())
            doRefresh.isRefreshing = false
        }

        presenter.getMyReport(act.applicationContext, sharedPreferenced.getValueUsername("Username").toString())

        rvAdapter = RVQuestReport { position ->
            val intent = Intent(context, DetailReportActivity::class.java).apply {
                putExtra(INTENT_EXTRA, filteredData[position].iDLaporan)
                putExtra(INTENT_STATUS_REPORT, filteredData[position].status)
                putExtra(DetailReportActivity.INTENT_SENDTINDAKAN_REPORT, filteredData[position].SendTindakan)
            }
            startActivity(intent)
            act.finish()
        }


        et_search_questreport.addTextChangedListener(object : TextWatcher{
            @SuppressLint("DefaultLocale")
            override fun afterTextChanged(p0: Editable?) {

                try {
                    val textToFilter = et_search_questreport.text.toString().toLowerCase().trim()
                    filteredData = loadData.filter { data -> data.tindakan.toLowerCase().contains(textToFilter)
                    }.toMutableList()

                    if (textToFilter.isNotEmpty()) {
                        rvAdapter.setData(filteredData)

                    } else {
                        rvAdapter.setData(filteredData)
                    }
                } catch (e: Exception){ }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        })
    }

    override fun onDataCompleteGetQuestReport(q: HomeDataModel) {

        try {

            loadData = q.responseData.toMutableList()

            filteredData = loadData

            if (loadData.isNullOrEmpty()){
                lottie_quest.makeVisibile()
                lottie_quest.setAnimation("notfound.json")
                lottie_quest.playAnimation()
                cv_quest_searchview.makeGone()
            } else {
                rv_quest.apply {
                    rvAdapter.setData(loadData)
                    layoutManager = LinearLayoutManager(context)
                    adapter = rvAdapter
                }
                lottie_quest.makeGone()
                cv_quest_searchview.makeVisibile()
            }
        } catch (e: Exception){   }
    }

    override fun onErrorGetQuestReport(t: Throwable) {
        lottie_quest.makeVisibile()
        lottie_quest.playAnimation()
        act.showToastErrorFromServer("Error From Server Get Tugas")
    }
}
