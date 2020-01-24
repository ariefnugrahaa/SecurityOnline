package com.example.arief.securityonline.presenter

import RestApi
import android.content.Context
import com.example.arief.securityonline.`interface`.BaseInterface
import com.pertamina.pdsi.securityonline.Model.*
import retrofit2.Call
import retrofit2.Response

class LoginPresenter(context: Context) {

    private val iLogin = context as BaseInterface.ILogin

    fun postData(context: Context, username: String, password: String) {
        RestApi.create(context).loginUser(username, password).enqueue(object : retrofit2.Callback<LoginModel> {
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    iLogin.onDataErrorLogin(t)
                }

                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    iLogin.onDataCompleteLogin(response.body() as LoginModel)
                }
            })
    }
}