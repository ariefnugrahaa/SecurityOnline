package com.example.arief.securityonline.network.presenter

import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.example.arief.securityonline.network.model.ListFollowUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetListFollowupPresenter (iListFollowupView: BaseInterface.IFollow){

    private val iListFollowup = iListFollowupView

    fun getListFollowup(context: Context, IdLaporan: String){
        RestApi.create(context).getFollowup(IdLaporan).enqueue(object : Callback<ListFollowUp>{
            override fun onFailure(call: Call<ListFollowUp>, t: Throwable) {
                iListFollowup.onDataErrorFollow(t)
            }

            override fun onResponse(call: Call<ListFollowUp>, response: Response<ListFollowUp>) {
                iListFollowup.onDataCompleteFollow(response.body() as ListFollowUp)
            }
        })
    }
}