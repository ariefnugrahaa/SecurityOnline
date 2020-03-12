package com.example.arief.securityonline.presentation.auth.login

import android.content.Context
import com.pertamina.pdsi.securityonline.Model.LoginModel
import java.lang.Exception

interface LoginContract {

    interface View{
        fun onDataSuccessLogin(q: LoginModel)
        fun onDataErrorLogin(t: Throwable)
        fun getViewContext(): Context
        fun showErrorToast(e: Exception)
        fun initViews()
    }

    interface Presenter{
        fun postData(username: String, password: String)
        fun attach(view: View)
        fun detach()
    }
}