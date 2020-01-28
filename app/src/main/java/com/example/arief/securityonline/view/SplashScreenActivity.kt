package com.example.arief.securityonline.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.arief.securityonline.R
import com.example.arief.securityonline.view.auth.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        val background = object : Thread(){
            override fun run() {
                try {
                    sleep(1000)
                    val intent = Intent(baseContext, LoginActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}

