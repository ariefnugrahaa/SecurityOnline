package com.example.arief.securityonline.view.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.network.SharedPrefManager
import com.example.arief.securityonline.presenter.LoginPresenter
import com.example.arief.securityonline.view.MainActivity
import com.example.arief.umkpdconline.common.showToastErrorFromServer
import com.example.arief.umkpdconline.common.showToastErrorLogin
import com.example.arief.umkpdconline.common.showToastSuccessLogin
import com.pertamina.pdsi.securityonline.Model.LoginModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity(), BaseInterface.ILogin {

    private val presenter by lazy {
        LoginPresenter(this)
    }

    private val sharedPreference by lazy {
        SharedPrefManager.getInstance(applicationContext)
    }

    private var username:String? = null
    private var password:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        isView()

    }

    override fun onDataCompleteLogin(q: LoginModel) {
        try {
        if (q.responseCode != 0) {
            showToastErrorLogin("Gagal Untuk Login")
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            btn_login.revertAnimation()
        } else {
            showToastSuccessLogin("Login Success")
            sharedPreference.saveToken(q.responseData.token)
            sharedPreference.saveUser(q.responseData.name)
            sharedPreference.saveNopeg(q.responseData.pernr)
            sharedPreference.saveId(q.responseData.username)
            val intent = Intent(this, MainActivity::class.java).apply {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    } catch (e:Exception){}

    }

    override fun onDataErrorLogin(e: Throwable) {
        showToastErrorFromServer("Error From Server")
    }

    private fun isView(){

        tv_forgetpassword.setOnClickListener {
            startActivity<ResetPasswordActivity>()
        }

        btn_signup.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        et_email.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                emailLogin()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        et_password.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                emailPassword()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        btn_login.setOnClickListener {
            username = et_email.text.toString()
            password = et_password.text.toString()


            if (!emailLogin() && !emailPassword()){
                showToastErrorLogin("Harap lengkapi Data")
            }else {
                btn_login.startAnimation()
                presenter.postData(this, username.toString(), password.toString())
//            if (checkbox.isChecked) {
//                sharedPreference.saveId(username)
//                sharedPreference.savePassword(password)
//                checkbox.isChecked = true
//            } else {
//                et_email.setText("")
//                et_password.setText("")
//            }
//
//            et_email.setText(sharedPreference.getValueId("id"))
//            et_password.setText(sharedPreference.getValuePassword("Password"))
            }
        }

    }

    private fun emailLogin():Boolean{

        val emailError = et_email.text!!.trim()

        return when{
            emailError.isEmpty() -> {
                et_login_email.error = "Email tidak boleh kosong"
                false
            } else -> {
                et_login_email.error = null
                true
            }
        }
    }

    private fun emailPassword():Boolean{

        val passwordError = et_password.text!!.trim()

        return when{
            passwordError.isEmpty() -> {
                et_login_password.error = "Password tidak boleh kosong"
                false
            } else -> {
                et_login_password.error = null
                true
            }
        }
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
