package com.example.arief.securityonline.network.presenter

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetListReportPresenter (iListReportView: BaseInterface.IListReportLatest){

    private val iListView = iListReportView

    fun getListReport(context: Context){
        RestApi.create(context).getLatest().enqueue(object : Callback<HomeDataModel>{
            override fun onFailure(call: Call<HomeDataModel>, t: Throwable) {
                iListView.onErrorListReport(t)
            }

            override fun onResponse(call: Call<HomeDataModel>, response: Response<HomeDataModel>) {
                iListView.onDataCompleteListReport(response.body() as HomeDataModel)
            }
        })
    }
}