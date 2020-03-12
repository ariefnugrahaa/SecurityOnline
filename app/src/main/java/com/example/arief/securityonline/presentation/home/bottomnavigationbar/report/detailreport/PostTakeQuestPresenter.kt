package com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.detailreport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.FinishQuestModel
import com.example.arief.securityonline.network.model.TakeQuestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostTakeQuestPresenter (iTakeQuestView: BaseInterface.ITakeQuest){

    private val iTakeQuest = iTakeQuestView

    private fun takeQuest():MutableMap<String, String>{

        val map: HashMap<String, String> = HashMap()
        map["idLaporan"] = DetailReportActivity.idLaporan.toString()
        map["takeby"] = DetailReportActivity.username.toString()
        map["status"] = "3"
        return map
    }

    fun postTakeQuest(context: Context){
        RestApi.create(context).postTakeQuest(takeQuest()).enqueue(object : Callback<TakeQuestModel>{
            override fun onFailure(call: Call<TakeQuestModel>, t: Throwable) {
                iTakeQuest.onDataErrorTakeQuest(t)
            }

            override fun onResponse(call: Call<TakeQuestModel>, response: Response<TakeQuestModel>) {
                iTakeQuest.onDataCompleteTakeQuest(response.body() as TakeQuestModel)
            }
        })
    }
}