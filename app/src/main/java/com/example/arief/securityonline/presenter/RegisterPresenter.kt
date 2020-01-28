package com.example.arief.securityonline.presenter

import android.content.Context
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.email
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.jabatan
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.lokasi
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.nameregister
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.nopek
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.passwordregister
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.perusahaan
import com.example.arief.securityonline.view.auth.RegisterActivity.Companion.username
import com.pertamina.pdsi.securityonline.Model.LoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(context: Context) {

    private var iView = context as BaseInterface.IRegister

    private fun register(): MutableMap<String, String>{
        val requestBody: MutableMap<String, String> = mutableMapOf()
        requestBody["username"] = username.toString()
        requestBody["password"] = passwordregister.toString()
        requestBody["name"] = nameregister.toString()
        requestBody["nopek"] = nopek.toString()
        requestBody["jabatan"] = jabatan.toString()
        requestBody["perusahaan"] = perusahaan.toString()
        requestBody["lokasi"] = lokasi.toString()
        requestBody["email"] = email.toString()
        requestBody["gtoken"] = "late init"

        return requestBody
    }

    fun postRegister(context: Context){
        RestApi.create(context).postRegister(register()).enqueue(object : Callback<LoginModel>{
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                iView.onDataErrorLogin(t)
            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                iView.onDataCompleteRegister(response.body() as LoginModel)
            }
        })
    }
}