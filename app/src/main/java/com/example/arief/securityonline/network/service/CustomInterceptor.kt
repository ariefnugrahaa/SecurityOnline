package com.example.arief.securityonline.network.service

import android.content.Context
import com.example.arief.securityonline.network.database.SharedPrefManager
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(contexts: Context):Interceptor {

    var context: Context = contexts

    override fun intercept(chain: Interceptor.Chain): Response {
        val token =  SharedPrefManager.getInstance(context).getValueToken("tokenAkses")
        var request = chain.request()

        if(token!!.isNotEmpty()) request = request.newBuilder().addHeader("Authentication", token.toString()).build()

        return chain.proceed(request)
    }
}