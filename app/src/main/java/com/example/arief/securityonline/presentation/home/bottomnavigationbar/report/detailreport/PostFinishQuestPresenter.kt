package com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.detailreport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.FinishQuestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFinishQuestPresenter (iFinishQuestView: BaseInterface.IFinishQuest){

    private val iFinishQuest = iFinishQuestView

    private fun sendQuest():MutableMap<String, String>{

        val map: HashMap<String, String> = HashMap()
        map["idLaporan"] = DetailReportActivity.idLaporan.toString()
        map["status"] = "4"

        return map

    }

    fun finishQuest(context: Context){
        RestApi.create(context).postFinishQuest(sendQuest()).enqueue(object : Callback<FinishQuestModel>{
            override fun onFailure(call: Call<FinishQuestModel>, t: Throwable) {
                iFinishQuest.onDataErrorFinishQuest(t)
            }

            override fun onResponse(call: Call<FinishQuestModel>, response: Response<FinishQuestModel>) {
                iFinishQuest.onDataCompleteFinishQuest(response.body() as FinishQuestModel)
            }
        })
    }
}