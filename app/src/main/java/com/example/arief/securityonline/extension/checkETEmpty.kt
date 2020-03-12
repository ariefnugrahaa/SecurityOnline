package com.example.arief.securityonline.extension

import android.widget.EditText

fun checkETEmpty(view: EditText, message: String ) : Boolean{
    if( view.text.isEmpty() ){
        view.error = "$message Kosong"
        return false
    }
    return true
}

fun checkETRegisterEmpty(view: EditText, message: String ) : Boolean{
    if( view.text.isEmpty() ){
        view.error = "$message Kosong"
        return false
    }
    return true
}

