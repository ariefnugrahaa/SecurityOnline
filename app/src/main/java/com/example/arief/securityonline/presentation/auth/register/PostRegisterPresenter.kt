package com.example.arief.securityonline.presentation.auth.register

import RestApi
import android.content.Context
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.email
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.jabatan
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.lokasi
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.nameregister
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.nopek
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.passwordregister
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.perusahaan
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity.Companion.username
import com.pertamina.pdsi.securityonline.Model.LoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRegisterPresenter: RegisterContract.Presenter {

    private var view: RegisterContract.View? = null

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

    override fun postData() {
        view?.getViewContext()?.let {context ->
            RestApi.create(context).postRegister(register()).enqueue(object : Callback<LoginModel>{
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    view?.onDataErrorRegister(t)
                }

                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    view?.onDataSuccessRegister(response.body() as LoginModel)
                }
            })
        }
    }

    override fun attach(view: RegisterContract.View) {
        this.view = view
        view.initViews()
    }

    override fun detach() {
        view = null
    }
}