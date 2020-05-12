package com.example.arief.securityonline.network.presenter

import RestApi
import android.content.Context
import com.example.arief.securityonline.network.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetProfilePresenter(iViewUser: BaseInterface.IUser) {

    private val iUser = iViewUser

//    fun postData(context: Context) {
//        RestApi.create(context).getProfile().enqueue(object : Callback<UserDataModel> {
//            override fun onFailure(call: Call<UserDataModel>, t: Throwable) {
//                iUser.onErrorCompleteUser(t)
//            }
//
//            override fun onResponse(call: Call<UserDataModel>, response: Response<UserDataModel>) {
//                iUser.onDataCompleteUser(response.body() as UserDataModel)
//            }
//        })
//    }
}