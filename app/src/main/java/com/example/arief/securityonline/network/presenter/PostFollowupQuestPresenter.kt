package com.example.arief.securityonline.network.presenter

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.FinishQuestModel
import com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.detailreport.DetailReportActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFollowupQuestPresenter (iFollowupQuestView: BaseInterface.IPostFollowupQuest){

    private val iFinishQuest = iFollowupQuestView

    private fun sendQuest():MutableMap<String, String>{

        val map: HashMap<String, String> = HashMap()
        map["idLaporan"] = DetailReportActivity.idLaporan.toString()
        map["followupby"] = DetailReportActivity.username.toString()
        map["status"] = "5"

        return map

    }

    fun followupQuest(context: Context){
        RestApi.create(context).postFollowupQuest(sendQuest()).enqueue(object : Callback<FinishQuestModel>{
            override fun onFailure(call: Call<FinishQuestModel>, t: Throwable) {
                iFinishQuest.onDataErrorFollowupQuest(t)
            }

            override fun onResponse(call: Call<FinishQuestModel>, response: Response<FinishQuestModel>) {
                iFinishQuest.onDataCompleteFollowupQuest(response.body() as FinishQuestModel)
            }
        })
    }
}