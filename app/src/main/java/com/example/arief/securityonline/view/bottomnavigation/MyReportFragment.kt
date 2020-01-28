package com.example.arief.securityonline.view.bottomnavigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.adapter.RVMyReport
import com.example.arief.securityonline.presenter.MyReportPresenter
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import kotlinx.android.synthetic.main.fragment_my_report.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class MyReportFragment : Fragment(), BaseInterface.IMyReport {


    private val presenter by lazy { MyReportPresenter(this) }
    private lateinit var rvMyReport: RVMyReport
    private lateinit var loadData:HomeDataModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvMyReport = RVMyReport { position -> null }
        presenter.getMyReport(act.applicationContext, "admin4")

    }

    override fun onDataCompleteMyReport(q: HomeDataModel) {

        loadData = q

        rv_myreport.apply {
            rvMyReport.setData(loadData.responseData.toMutableList())
            layoutManager = LinearLayoutManager(context)
            adapter = rvMyReport
        }
    }

    override fun onErrorMyReport(t: Throwable) {
        toast("Tidak Bisa Mendapatkan Report")
    }
}
