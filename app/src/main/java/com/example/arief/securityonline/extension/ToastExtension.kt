package com.example.arief.securityonline.extension

import android.content.Context
import android.widget.Toast
import com.example.arief.securityonline.R
import com.muddzdev.styleabletoast.StyleableToast


fun Context.showToastSuccessLogin(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_SHORT, R.style.loginSuccess).show()
}

fun Context.showToastErrorLogin(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_SHORT, R.style.loginFail).show()
}

fun Context.showToastErrorFromServer(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_SHORT, R.style.ServerError).show()
}

fun Context.showToastSuccessRegister(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_SHORT, R.style.loginSuccess).show()
}

fun Context.showToastErrorRegister(message: String) {
    StyleableToast.makeText(this, message, Toast.LENGTH_SHORT, R.style.loginFail).show()
}




