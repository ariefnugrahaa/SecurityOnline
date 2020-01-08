package com.example.arief.securityonline.`interface`

import com.pertamina.pdsi.securityonline.Model.LoginModel
import java.lang.Error

interface iLogin {
    fun onDataCompleteFromApi(q: LoginModel)
    fun onDataErrorFromApi(e: Throwable)
}