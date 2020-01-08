package com.example.arief.securityonline.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arief.securityonline.R
import com.example.arief.securityonline.network.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_notifications.*

class UserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btn_logout.setOnClickListener {
            logout()
        }

    }

    private fun logout() {
        if (SharedPrefManager.getInstance(activity!!.applicationContext).clear()) {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}