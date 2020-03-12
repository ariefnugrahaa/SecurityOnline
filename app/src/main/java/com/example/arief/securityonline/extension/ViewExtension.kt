package com.example.arief.securityonline.extension

import android.R
import android.content.Context
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText

fun View.makeVisibile(){
    this.visibility = View.VISIBLE
}

fun View.makeGone(){
    this.visibility = View.GONE
}


fun spinnerAdapterr(context: Context, listSpinner: ArrayList<String>): ArrayAdapter<String>{

    val adapter = ArrayAdapter(context, R.layout.simple_spinner_item, listSpinner)
    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

    return adapter
}

fun Window.freezeLayout(){
    setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}




