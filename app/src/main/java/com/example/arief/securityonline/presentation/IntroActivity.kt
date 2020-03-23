package com.example.arief.securityonline.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arief.securityonline.R
import com.example.arief.securityonline.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_intro.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        btn_intro.onClick { startActivity<MainActivity>() }

    }
}
