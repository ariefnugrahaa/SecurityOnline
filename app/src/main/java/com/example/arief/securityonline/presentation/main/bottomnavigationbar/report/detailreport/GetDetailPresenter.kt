package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDetailPresenter(DetailView: BaseInterface.IGetDetail) {

    val iListGetDetail = DetailView

    fun getDetailReport(context: Context, idLaporan:String){
        RestApi.create(context).getDetailReport(idLaporan).enqueue( object : Callback<HomeDataModel>{
            override fun onFailure(call: Call<HomeDataModel>, t: Throwable) {
                iListGetDetail.onErrorGetDetail(t)
            }

            override fun onResponse(call: Call<HomeDataModel>, response: Response<HomeDataModel>) {
                iListGetDetail.onDataCompleteGetDetail(response.body() as HomeDataModel)
            }
        })
    }
}