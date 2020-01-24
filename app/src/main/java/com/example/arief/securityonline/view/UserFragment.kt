package com.example.arief.securityonline.view

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
import com.pertamina.pdsi.securityonline.Model.UserDataModel
import kotlinx.android.synthetic.main.activity_login.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val userName =  SharedPrefManager.getInstance(activity!!.applicationContext).getValueUser("Username")
        val jabatann = SharedPrefManager.getInstance(activity!!.applicationContext).getValueStringNopeg("Jabatan")

        tv_user.text = userName.toString()
        tv_nopeg.text = jabatann.toString()

        btn_logout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        if (SharedPrefManager.getInstance(activity!!.applicationContext).clear()) {
            val intent = Intent(context, LoginActivity::class.java).apply {
                putExtra("asdasd", sharedPreference.getValueId("a"))
            }
            startActivity(intent)
        }
    }

    override fun onDataCompleteUser(q: UserDataModel) {
    }

    override fun onErrorCompleteUser(t: Throwable) {
    }
}