package com.example.arief.securityonline.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arief.securityonline.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_EXTRA = "a.b"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
