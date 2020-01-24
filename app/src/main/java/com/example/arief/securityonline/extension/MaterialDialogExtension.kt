package com.example.arief.securityonline.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.example.arief.securityonline.R
import com.shashank.sony.fancygifdialoglib.FancyGifDialog
import com.shreyaspatil.MaterialDialog.MaterialDialog


@SuppressLint("RestrictedApi")
fun Context.openingDialog(context: Context){
    MaterialDialog.Builder(context as Activity)
        .setTitle("Welcome To")
        .setMessage("Security Online")
//        .setCancelable(false)
        .setPositiveButton("Ya", R.drawable.ic_create) { a, i ->
            a.dismiss()
        }
        .setNegativeButton("Tidak", R.drawable.ic_account) { _, i ->

        }

        .setAnimation("securityonline.json")
        .build()
        .show()
}

fun Context.openingDialog1(context: Context){
    FancyGifDialog.Builder(context as Activity)
        .setTitle("Welcome To")
        .setMessage("Security Online")
        .setPositiveBtnText("Ok")
        .setPositiveBtnBackground("#FF4081")
        .setGifResource(R.drawable.securityy) //Pass your Gif here
        .isCancellable(true)
        .OnPositiveClicked { }
        .build()

}