package com.example.arief.securityonline.view.bottomnavigation


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.adapter.RVLatestReport
import com.example.arief.securityonline.presenter.ListReportPresenter
import com.example.arief.securityonline.view.DetailActivity
import com.example.arief.securityonline.view.DetailActivity.Companion.INTENT_EXTRA
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import kotlinx.android.synthetic.main.fragment_latest_report.*
import org.jetbrains.anko.support.v4.act


class LatestReportFragment : Fragment(), BaseInterface.IListReportLatest {

    private val presenter by lazy {
        ListReportPresenter(this)
    }

    private lateinit var rvLatest:RVLatestReport
    private lateinit var loadData:HomeDataModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvLatest = RVLatestReport { position ->
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(INTENT_EXTRA, loadData.responseData[position].iDLaporan)
            }
        }
        presenter.getListReport(act.applicationContext)
    }

    override fun onDataCompleteListReport(q: HomeDataModel) {

        loadData = q

        rv_latest_report.apply {
            rvLatest.setData(loadData.responseData.toMutableList())
            layoutManager = LinearLayoutManager(context)
            adapter = rvLatest
        }
    }

    override fun onErrorListReport(t: Throwable) {

    }
}
