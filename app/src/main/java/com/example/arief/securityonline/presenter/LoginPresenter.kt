package com.example.arief.securityonline.presenter

import android.content.Context
import com.example.arief.securityonline.`interface`.iLogin
import com.pertamina.pdsi.securityonline.Model.LoginModel
import retrofit2.Call
import retrofit2.Response

class LoginPresenter(context: Context) {

    val iLogin = context as iLogin

    fun postData(context: Context, username: String, password: String){
        RestApi.create(context).loginUser(username, password).enqueue(object : retrofit2.Callback<LoginModel>{
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                iLogin.onDataErrorFromApi(t)
            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                iLogin.onDataCompleteFromApi(response.body() as LoginModel)
            }
        })
    }
}