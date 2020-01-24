package com.example.arief.securityonline.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.adapter.RVLatestReport
import com.example.arief.securityonline.presenter.ListReportPresenter
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import org.jetbrains.anko.support.v4.act

/**
 * A simple [Fragment] subclass.
 */
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

        rvLatest = RVLatestReport { position -> null }
//        presenter.getListReport(act.applicationContext)

    }

    override fun onDataCompleteListReport(q: HomeDataModel) {
        loadData = q


    }

    override fun onErrorListReport(t: Throwable) {

    }


}
