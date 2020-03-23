package com.example.arief.securityonline.network.presenter

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.PostFollowupModel
import com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport.DetailReportActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostTableFollowupPresenter (iFollowUpView: BaseInterface.IFollowup){

    private val iFollowup = iFollowUpView

    private fun followUp():MutableMap<String, String>{

        val map: HashMap<String, String> = HashMap()
        map["id_laporan"] = DetailReportActivity.idLaporan.toString()
        map["tindakan"] = DetailReportActivity.tindakanPenyelesaian.toString()
        map["kerugian"] = DetailReportActivity.kerugian.toString()
        map["created_by"] = DetailReportActivity.username.toString()

        return map

    }

    fun postTableFollowup(context: Context){
        RestApi.create(context).postFollowup(followUp()).enqueue(object : Callback<PostFollowupModel>{
            override fun onFailure(call: Call<PostFollowupModel>, t: Throwable) {
                iFollowup.onDataErrorFollowup(t)
            }

            override fun onResponse(call: Call<PostFollowupModel>, response: Response<PostFollowupModel>) {
                iFollowup.onDataCompleteFollowup(response.body() as PostFollowupModel)
            }
        })
    }
}