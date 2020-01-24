package com.example.arief.securityonline.view

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.network.SharedPrefManager
import com.example.arief.securityonline.presenter.LoginPresenter
import com.pertamina.pdsi.securityonline.Model.LoginModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), BaseInterface.ILogin {

    private val presenter by lazy {
        LoginPresenter(this)
    }

    private val sharedPreference by lazy {
        SharedPrefManager.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        val a = et_email.setText(sharedPreference.getValueId("id")).toString()
//        val b = et_password.setText(sharedPreference.getValuePassword("Password")).toString()

//        if (a == "id" && b == "Password"){
//            et_email.setText("")
//            et_password.setText("")
//        }



//        val sharePrefId = sharedPreference.getValueId("id")
//        val sharePrefPassword = sharedPreference.getValuePassword("Password")
//
//        if (sharePrefId!!.isEmpty() && sharePrefPassword!!.isEmpty()){
//            et_email.setText(" ")
//            et_password.setText(" ")
//        } else {
//            et_email.setText(sharedPreference.getValueId("id"))
//            et_password.setText(sharedPreference.getValuePassword("Password"))
//        }
//
//        if (sharePrefId.isNullOrEmpty() && sharePrefPassword.isNullOrEmpty()){
//            et_email.setText("")
//            et_password.setText("")
//        } else {
//            checkbox.isChecked = true
//            et_email.setText(sharedPreference.getValueId("id"))
//            et_password.setText(sharedPreference.getValuePassword("Password"))
//        }


        tv_forgetpassword.setOnClickListener {
            startActivity<ResetPasswordActivity>()
        }

        btn_signup.setOnClickListener {
            startActivity<RegisterActivity>()
        }



        btn_login.setOnClickListener {
            val username = et_email.text.toString()
            val password = et_password.text.toString()

            if (checkbox.isChecked) {
                sharedPreference.saveId(username)
                sharedPreference.savePassword(password)
                checkbox.isChecked = true
            } else {
                et_email.setText("")
                et_password.setText("")
            }

            et_email.setText(sharedPreference.getValueId("id"))
            et_password.setText(sharedPreference.getValuePassword("Password"))

            presenter.postData(this, username, password)

        }
    }

    override fun onDataCompleteLogin(q: LoginModel) {
        try {
        if (q.responseCode != 0) {
            toast("Gagal Untuk Login")
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//            btn_login.revertAnimation()
        } else {
            toast("Login Success")
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
