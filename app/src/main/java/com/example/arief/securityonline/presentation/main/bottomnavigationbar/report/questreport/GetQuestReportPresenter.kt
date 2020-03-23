package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.questreport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetQuestReportPresenter(iQuestReportView: BaseInterface.IGetQuestReport) {

    private var iView = iQuestReportView

    fun getMyReport(context: Context, userId: String){
        RestApi.create(context).getQuestReport(userId).enqueue(object : Callback<HomeDataModel>{
            override fun onFailure(call: Call<HomeDataModel>, t: Throwable) {
                iView.onErrorGetQuestReport(t)
            }

            override fun onResponse(call: Call<HomeDataModel>, response: Response<HomeDataModel>) {
                iView.onDataCompleteGetQuestReport(response.body() as HomeDataModel)
            }
        })
    }
}