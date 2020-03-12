package com.example.arief.securityonline.presentation.auth.register

import android.content.Context
import com.pertamina.pdsi.securityonline.Model.LoginModel
import java.lang.Exception

interface RegisterContract {

    interface View{
        fun onDataSuccessRegister(q: LoginModel)
        fun onDataErrorRegister(t: Throwable)
        fun getViewContext(): Context
        fun showErrorToast(e: Exception)
        fun initViews()
    }

    interface Presenter{
        fun postData()
        fun attach(view: View)
        fun detach()
    }
}