package com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.myreport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMyReportPresenter(iMyReportView: BaseInterface.IMyReport) {

    private var iView = iMyReportView

    fun getMyReport(context: Context, userId: String){
        RestApi.create(context).getMyreport(userId).enqueue(object : Callback<HomeDataModel>{
            override fun onFailure(call: Call<HomeDataModel>, t: Throwable) {
                iView.onErrorMyReport(t)
            }

            override fun onResponse(call: Call<HomeDataModel>, response: Response<HomeDataModel>) {
                iView.onDataCompleteMyReport(response.body() as HomeDataModel)
            }
        })
    }
}