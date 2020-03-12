package com.example.arief.securityonline.presentation.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.arief.securityonline.R
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.example.arief.securityonline.presentation.IntroActivity
import com.example.arief.securityonline.presentation.auth.register.RegisterActivity
import com.example.arief.securityonline.presentation.auth.resetpassword.ResetPasswordActivity
import com.example.arief.securityonline.presentation.home.MainActivity
import com.example.arief.umkpdconline.common.showToastErrorFromServer
import com.example.arief.umkpdconline.common.showToastErrorLogin
import com.example.arief.umkpdconline.common.showToastSuccessLogin
import com.pertamina.pdsi.securityonline.Model.LoginModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val presenter by lazy { PostLoginPresenter() }

    private val sharedPreference by lazy {
        SharedPrefManager.getInstance(applicationContext)
    }

    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attach(this)

    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun initViews() {
        isView()
    }

    override fun onDataSuccessLogin(q: LoginModel) {
        try {

            if (q.responseCode != 0) {
                showToastErrorLogin("Gagal Untuk Login")
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                btn_login.revertAnimation()

            } else {

                showToastSuccessLogin("Login Success")
                sharedPreference.saveToken(q.responseData!!.token)
                sharedPreference.saveName(q.responseData!!.name)
                sharedPreference.saveUserName(q.responseData!!.username)
                sharedPreference.saveNopeg(q.responseData!!.pernr)
                sharedPreference.saveRole(q.responseData!!.role)
                sharedPreference.saveEmail(q.responseData!!.email)

                btn_login.revertAnimation()
                startActivity(intentFor<IntroActivity>().newTask())
                finish()
            }
        } catch (e: Exception) {

        }
    }

    override fun onDataErrorLogin(t: Throwable) {
        try {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            btn_login.revertAnimation()
            showToastErrorFromServer("Error From Server")

        } catch (e: Exception) {
        }
    }

    override fun getViewContext(): Context = this

    override fun showErrorToast(e: java.lang.Exception) = Unit

    private fun isView() {

        tv_forgetpassword.setOnClickListener { startActivity<ResetPasswordActivity>() }

        btn_signup.setOnClickListener { startActivity<RegisterActivity>() }

        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                emailLogin()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                emailPassword()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        })

        btn_login.setOnClickListener {
            username = et_email.text.toString()
            password = et_password.text.toString()

            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            if (!emailLogin() && !emailPassword()) {
                showToastErrorLogin("Harap lengkapi Data")
            } else {
                btn_login.startAnimation()
                presenter.postData(username.toString(), password.toString())
            }
        }
    }

    private fun emailLogin(): Boolean {

        val emailError = et_email.text!!.trim()

        return when {
            emailError.isEmpty() -> {
                et_login_email.error = "Email tidak boleh kosong"
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                false
            }
            else -> {
                et_login_email.error = null
                true
            }
        }
    }

    private fun emailPassword(): Boolean {

        val passwordError = et_password.text!!.trim()

        return when {
            passwordError.isEmpty() -> {
                et_login_password.error = "Password tidak boleh kosong"
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                false
            }
            else -> {
                et_login_password.error = null
                true
            }
        }
    }

    override fun onStart() {
        try {
            if (SharedPrefManager.getInstance(this).isLoggedIn()) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        } catch (e: java.lang.Exception){ }
        super.onStart()
    }
}
