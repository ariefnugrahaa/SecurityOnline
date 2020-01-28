package com.example.arief.umkpdconline.common

import android.content.Context
import android.widget.Toast
import com.example.arief.securityonline.R
import com.muddzdev.styleabletoast.StyleableToast


fun Context.showToastSuccessLogin(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_LONG, R.style.loginSuccess).show()
}

fun Context.showToastErrorLogin(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_LONG, R.style.loginFail).show()
}

fun Context.showToastErrorFromServer(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_LONG, R.style.ServerError).show()
}

fun Context.showToastSuccessRegister(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_LONG, R.style.loginSuccess).show()
}

fun Context.showToastErrorRegister(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_LONG, R.style.loginFail).show()
}




