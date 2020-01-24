package com.example.arief.securityonline.extension

import android.R
import android.content.Context
import android.view.View
import android.widget.ArrayAdapter

fun View.makeVisibile(){
    this.visibility = View.VISIBLE
}

fun View.makeGone(){
    this.visibility = View.GONE
}


fun Context.spinnerAdapterr(context: Context, listSpinner: ArrayList<String>): ArrayAdapter<String>{

    val adapter = ArrayAdapter(context, R.layout.simple_spinner_item, listSpinner)
    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

    return adapter
}


