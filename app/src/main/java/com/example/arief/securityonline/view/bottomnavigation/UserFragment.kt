package com.example.arief.securityonline.view.bottomnavigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arief.securityonline.R
import com.example.arief.securityonline.`interface`.BaseInterface
import com.example.arief.securityonline.network.SharedPrefManager
import com.example.arief.securityonline.presenter.ProfilePresenter
import com.example.arief.securityonline.view.auth.LoginActivity
import com.example.arief.umkpdconline.common.showSnackbarSuccess
import com.pertamina.pdsi.securityonline.Model.UserDataModel
import com.shreyaspatil.MaterialDialog.MaterialDialog
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.act

class UserFragment : Fragment(), BaseInterface.IUser {

    private val presenter by lazy {
        ProfilePresenter(this)
    }

    private val sharedPreference by lazy {
        SharedPrefManager.getInstance(act.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        presenter.postData(act.applicationContext)


        btn_logout.setOnClickListener {

            MaterialDialog.Builder(this.act)
                .setTitle("Logout")
                .setMessage("Ingin Keluar Dari Aplikasi Security Online ?")
                .setCancelable(false)
                .setPositiveButton("Ya", R.drawable.ic_check) { _, i ->
                    logout()
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
        }
    }

    override fun onDataCompleteUser(q: UserDataModel) {
        try {
            tv_user.text = q.responseData.name
            tv_nopeg.text = q.responseData.nopek
            tv_profile_email.text = q.responseData.email

        } catch (e: Exception){}
    }

    override fun onErrorCompleteUser(t: Throwable){

    }
}