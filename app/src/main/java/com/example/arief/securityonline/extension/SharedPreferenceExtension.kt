package com.example.arief.securityonline.extension

import android.content.Context
import com.example.arief.securityonline.network.SharedPrefManager

fun Context.sharedPreferenceUser(data: String){
    SharedPrefManager.getInstance(this).getValueId(data)
}