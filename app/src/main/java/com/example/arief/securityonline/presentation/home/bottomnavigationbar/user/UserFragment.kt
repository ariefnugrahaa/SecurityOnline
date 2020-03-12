package com.example.arief.securityonline.presentation.home.bottomnavigationbar.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arief.securityonline.R
import com.example.arief.securityonline.network.database.SharedPrefManager
import com.example.arief.securityonline.presentation.auth.login.LoginActivity
import com.example.arief.umkpdconline.common.showSnackbarSuccess
import com.shreyaspatil.MaterialDialog.MaterialDialog
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.act

class UserFragment : Fragment() {

//    private val presenter by lazy {
//        ProfilePresenter(this)
//    }

    private val sharedPreference by lazy {
        SharedPrefManager.getInstance(act.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val getUsername = sharedPreference.getValueUsername("Username").toString()
        val getName = sharedPreference.getValueName("Name").toString()
        val getEmail = sharedPreference.getValueEmail("Email").toString()
        val getNopeg = sharedPreference.getValueStringNopeg("Nopeg").toString()


        tv_profile_name.text = getName
        tv_profile_username.text = getUsername
        tv_profile_nopeg.text = getNopeg
        tv_profile_email.text = getEmail

//        presenter.postData(act.applicationContext)

        btn_logout.setOnClickListener {

            MaterialDialog.Builder(this.act)
                .setTitle("Logout")
                .setMessage("Ingin Keluar Dari Aplikasi Security Online ?")
                .setCancelable(false)
                .setPositiveButton("Ya", R.drawable.ic_check) { dialog, i ->
                    dialog.dismiss()
                    logout()
                    act.finish()
                }
                .setNegativeButton("Tidak", R.drawable.ic_close) { dialogInterface, _ ->
                    act.showSnackbarSuccess("Selamat Beraktifitas Kembali")
                    dialogInterface.dismiss()
                }
                .setAnimation("bag_error.json")
                .build()
                .show()
        }
    }

    private fun logout() {
        if (SharedPrefManager.getInstance(activity!!.applicationContext).clear()) {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            act.finish()
        }
    }
}