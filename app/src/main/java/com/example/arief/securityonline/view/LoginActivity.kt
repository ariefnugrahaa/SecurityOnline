package com.example.arief.securityonline.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.iLogin
import com.example.arief.securityonline.network.SharedPrefManager
import com.example.arief.securityonline.presenter.LoginPresenter
import com.pertamina.pdsi.securityonline.Model.LoginModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), iLogin {

    private val presenter by lazy {
        LoginPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = et_email.text.toString()
        val password = et_password.text.toString()

        btn_signup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            presenter.postData(this, username, password)
        }

    }

    override fun onDataCompleteFromApi(q: LoginModel) {
        if (q.responseCode != 0) {
            toast("Gagal Untuk Login")
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//            btn_login.revertAnimation()
        } else {
            toast("Login Success")
            SharedPrefManager.getInstance(applicationContext).saveToken(q.responseData.token)
//            SharedPrefManager.getInstance(applicationContext).saveUser(q.userProfile.fullName.toString())
//            SharedPrefManager.getInstance(applicationContext).saveJabatan(q.userProfile.jabatan.toString())

            val intent = Intent(this, MainActivity::class.java).apply {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }

    override fun onDataErrorFromApi(e: Throwable) {
        toast("Error")
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }


}
