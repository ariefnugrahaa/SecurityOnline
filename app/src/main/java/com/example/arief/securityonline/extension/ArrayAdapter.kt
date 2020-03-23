package com.example.arief.securityonline.extension

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import org.jetbrains.anko.support.v4.act

fun Context.ArrAdaper(arr: Array<String>): ArrayAdapter<String>{

    return ArrayAdapter(this, R.layout.simple_list_item_1, arr)

}