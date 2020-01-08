package com.example.arief.securityonline.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(contexts: Context):Interceptor {

    var context: Context = contexts

    override fun intercept(chain: Interceptor.Chain): Response {
        val token =  SharedPrefManager.getInstance(context).getValueToken("tokenAkses")
        var request = chain.request()
        request = request.newBuilder().addHeader("Token", token.toString()).build()
        return chain.proceed(request)
    }
}