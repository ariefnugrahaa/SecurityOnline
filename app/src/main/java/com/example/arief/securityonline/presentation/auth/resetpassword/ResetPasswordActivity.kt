package com.example.arief.securityonline.presentation.auth.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arief.securityonline.R
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        btn_resetpassword.setOnClickListener {
            finish()
        }
    }
}
