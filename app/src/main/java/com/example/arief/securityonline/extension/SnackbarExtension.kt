package com.example.arief.umkpdconline.common

import android.app.Activity
import android.content.Context
import de.mateware.snacky.Snacky
import org.jetbrains.anko.act
import org.jetbrains.anko.support.v4.act

fun Activity.showSnackbarWarning(message: String){
    Snacky.builder()
        .setActivity(this)
        .setText(message)
        .setDuration(Snacky.LENGTH_SHORT)
        .warning()
        .show()
}

fun Activity.showSnackbarSuccess(message: String){
    Snacky.builder()
        .setActivity(this)
        .setText(message)
        .setDuration(Snacky.LENGTH_SHORT)
        .success()
        .show()
}

fun Activity.showSnackbarError(message: String){
    Snacky.builder()
        .setActivity(this)
        .setText(message)
        .setDuration(Snacky.LENGTH_SHORT)
        .error()
        .show()
}