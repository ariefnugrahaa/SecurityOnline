package com.example.arief.securityonline.presentation.home.bottomnavigationbar.writereport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arief.securityonline.R
import com.example.arief.securityonline.presentation.home.MainActivity
import kotlinx.android.synthetic.main.activity_success_report.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class SuccessReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_report)

        btn_back_success_report.onClick {
            startActivity<MainActivity>()
            finish()
        }

    }
}
