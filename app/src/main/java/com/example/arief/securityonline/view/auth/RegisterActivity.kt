package com.example.arief.securityonline.view.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.presenter.RegisterPresenter
import com.example.arief.umkpdconline.common.showToastErrorFromServer
import com.example.arief.umkpdconline.common.showToastErrorRegister
import com.example.arief.umkpdconline.common.showToastSuccessRegister
import com.pertamina.pdsi.securityonline.Model.LoginModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class RegisterActivity : AppCompatActivity(), BaseInterface.IRegister {

    private val presenter by lazy {
        RegisterPresenter(this)
    }

    companion object {
        var username: String? = null
        var passwordregister: String? = null
        var nameregister: String? = null
        var nopek: String? = null
        var jabatan: String? = null
        var perusahaan: String? = null
        var lokasi: String? = null
        var email: String? = null
        var gtoken: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegisterOnClick()

    }

    private fun btnRegisterOnClick(){

        btn_register.onClick {
            username = et_register_username.text.toString()
            passwordregister = et_register_password.text.toString()
            nameregister = et_register_nama.text.toString()
            nopek = et_register_nopek.text.toString()
            jabatan = et_register_jabatan.text.toString()
            perusahaan = et_register_perusahaan.text.toString()
            lokasi = et_register_lokasi.text.toString()
            email = et_register_email.text.toString()

            presenter.postRegister(this@RegisterActivity)
        }
    }

    override fun onDataCompleteRegister(q: LoginModel) {
        try {

            if (q.acknowledge == true) {
                showToastSuccessRegister("Berhasil Registrasi")
                startActivity<LoginActivity>()
            } else {
                showToastErrorRegister("Gagal Registrasi")
            }

        } catch (e: Exception){}
    }

    override fun onDataErrorLogin(e: Throwable) {
        showToastErrorFromServer("Server Error")
    }
}
