package com.example.arief.securityonline.presentation.auth.register

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.arief.securityonline.R
import com.example.arief.securityonline.extension.checkETRegisterEmpty
import com.example.arief.securityonline.presentation.auth.login.LoginActivity
import com.example.arief.umkpdconline.common.showToastErrorFromServer
import com.example.arief.umkpdconline.common.showToastErrorRegister
import com.example.arief.umkpdconline.common.showToastSuccessRegister
import com.pertamina.pdsi.securityonline.Model.LoginModel
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private val presenter by lazy {
        PostRegisterPresenter()
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter.attach(this@RegisterActivity)

    }

    override fun onDataSuccessRegister(q: LoginModel) {
        try {
            if (q.acknowledge) {
                showToastSuccessRegister("Berhasil Registrasi")
                btn_register.revertAnimation()
                startActivity<LoginActivity>()
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                finish()
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                showToastErrorRegister("Gagal Registrasi")
                btn_register.revertAnimation()
            }
        } catch (e: Exception){ }

    }

    override fun onDataErrorRegister(t: Throwable) {
        btn_register.revertAnimation()
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        showToastErrorFromServer("Server Error")
    }

    override fun getViewContext(): Context  = this

    override fun showErrorToast(e: java.lang.Exception) = Unit

    override fun initViews() {

        btn_register.onClick {

            btn_register.startAnimation()

            if (!validateInput()) {
                toast("Data Tidak Lengkap")
            } else {

                username = et_register_username.text.toString()
                passwordregister = et_register_password.text.toString()
                nameregister = et_register_namalengkap.text.toString()
                nopek = et_register_nopek.text.toString()
                jabatan = et_register_jabatan.text.toString()
                perusahaan = et_register_perusahaan.text.toString()
                lokasi = et_register_lokasi.text.toString()
                email = et_register_email.text.toString()

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

                presenter.postData()
            }
        }
    }

    private fun validateInput():Boolean{

        val status = true

        if (!checkETRegisterEmpty(et_register_username, "Username Tidak Boleh Kosong")){ !status }
        if (!checkETRegisterEmpty(et_register_email, "Username Tidak Boleh Kosong")){ !status }
        if (!checkETRegisterEmpty(et_register_namalengkap, "Username Tidak Boleh Kosong")){ !status }
        if (!checkETRegisterEmpty(et_register_nopek, "Username Tidak Boleh Kosong")){ !status }
        if (!checkETRegisterEmpty(et_register_jabatan, "Username Tidak Boleh Kosong")){ !status }
        if (!checkETRegisterEmpty(et_register_perusahaan, "Username Tidak Boleh Kosong")){ !status }
        if (!checkETRegisterEmpty(et_register_lokasi, "Username Tidak Boleh Kosong")){ !status }
        if (!checkETRegisterEmpty(et_register_password, "Username Tidak Boleh Kosong")){ !status }

        return status

    }


}
