package com.example.arief.securityonline.presentation.home.bottomnavigationbar.report.listuser

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.ListRoleModel
import com.example.arief.securityonline.network.model.SendQuestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetListRolePresenter (iListRoleView: BaseInterface.IGetListRole, iPostQuestView: BaseInterface.IPostSendQuest){

    private val iListView = iListRoleView
    private val iPostQuest = iPostQuestView

    fun getListReport(context: Context){
        RestApi.create(context).getListRoleUsers().enqueue(object : Callback<ListRoleModel>{
            override fun onFailure(call: Call<ListRoleModel>, t: Throwable) {
                iListView.onErrorGetListRole(t)
            }

            override fun onResponse(call: Call<ListRoleModel>, response: Response<ListRoleModel>) {
                iListView.onDataCompleteGetListRole(response.body() as ListRoleModel)
            }
        })
    }

    private fun sendQuest():MutableMap<String, String>{

        val map: HashMap<String, String> = HashMap()
        map["tindakan"] = ListRoleActivity.getUser.toString()
        map["idLaporan"] = ListRoleActivity.getExtra.toString()
        map["status"] = "2"

        return map

    }

    fun postSendQuest(context: Context){
        RestApi.create(context).postSendQuest(sendQuest()).enqueue(object : Callback<SendQuestModel>{
            override fun onFailure(call: Call<SendQuestModel>, t: Throwable) {
                iPostQuest.onErrorPostRequestReport(t)
            }

            override fun onResponse(call: Call<SendQuestModel>, response: Response<SendQuestModel>) {
                iPostQuest.onDataCompletePostQuestReport(response.body() as SendQuestModel)
            }
        })
    }
}