package com.example.arief.securityonline.presentation.main.bottomnavigationbar.report.detailreport

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.ApproveQuestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostApprovePresenter (iApproveView: BaseInterface.IApproveQuest){

    private val iApprove = iApproveView

    private fun approve():MutableMap<String, String>{

        val map: HashMap<String, String> = HashMap()
        map["idLaporan"] = DetailReportActivity.idLaporan.toString()
        map["approveby"] = DetailReportActivity.username.toString()
        map["status"] = "6"

        return map

    }

    fun postApprove(context: Context){
        RestApi.create(context).postApproveQuest(approve()).enqueue(object : Callback<ApproveQuestModel>{
            override fun onFailure(call: Call<ApproveQuestModel>, t: Throwable) {
                iApprove.onDataErrorApproveQuest(t)
            }

            override fun onResponse(call: Call<ApproveQuestModel>, response: Response<ApproveQuestModel>) {
                iApprove.onDataCompleteApproveQuest(response.body() as ApproveQuestModel)
            }
        })
    }
}