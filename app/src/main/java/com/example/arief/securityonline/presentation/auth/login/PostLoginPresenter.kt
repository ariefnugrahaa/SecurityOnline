package com.example.arief.securityonline.presentation.auth.login

import RestApi
import com.pertamina.pdsi.securityonline.Model.*
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class PostLoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null

    override fun attach(view: LoginContract.View) {
        this.view = view
        view.initViews()
    }

    override fun postData(username: String, password: String) {
        view?.getViewContext()?.let { context ->
            RestApi
                .create(context)
                .loginUser(username, password)
                .enqueue(object : retrofit2.Callback<LoginModel> {
                    override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                        view?.onDataErrorLogin(t)
                    }

                    override fun onResponse(
                        call: Call<LoginModel>,
                        response: Response<LoginModel>
                    ) {
                        try {
                            view?.onDataSuccessLogin(response.body() as LoginModel)
                        } catch (e: Exception) {
                            view?.showErrorToast(e)
                        }
                    }
                })
        }
    }

    override fun detach() {
        view = null
    }
}