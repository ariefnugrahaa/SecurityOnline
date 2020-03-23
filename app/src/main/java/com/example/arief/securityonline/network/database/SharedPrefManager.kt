package com.example.arief.securityonline.network.database

import android.content.Context

class SharedPrefManager private constructor(private val mCtx: Context){

    companion object{
        private const val SHARED_PREF_TOKEN = "my_shared_pref"
        private const val SHARED_PREF_NAME = "my_shared_user"
        private const val SHARED_PREF_NOPEG = "my_shared_jabatan"
        private const val SHARED_PREF_PASSWORD = "my_shared_password"
        private const val SHARED_PREF_USERNAME = "my_shared_username"
        private const val SHARED_PREF_ROLE = "my_shared_id"
        private const val SHARED_PREF_EMAIL = "my_shared_email"

        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null){
                mInstance =
                    SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

    fun isLoggedIn(): Boolean {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_TOKEN, Context.MODE_PRIVATE)
        return sharedPreferences.getString("tokenAkses", null) != null
    }

    fun saveToken(token: String){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_TOKEN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("tokenAkses", token)
        editor.apply()
    }

    fun saveName(user: String){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Name", user)
        editor.apply()
    }

    fun saveNopeg(jabatan: String){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NOPEG, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Nopeg", jabatan)
        editor.apply()
    }


    fun saveUserName(id:String){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_USERNAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Username", id)
        editor.apply()
    }

    fun saveEmail(id:String){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_EMAIL, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Email", id)
        editor.apply()
    }

    fun savePassword(password: String){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_PASSWORD, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Password", password)
        editor.apply()
    }

    fun saveRole(password: String){
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_ROLE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Role", password)
        editor.apply()
    }


    fun getValueUsername(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_USERNAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Username")
    }


    fun getValueToken(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_TOKEN, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "tokenAkses")
    }

    fun getValueRole(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_ROLE, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Role")
    }

    fun getValueName(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Name")
    }

    fun getValueEmail(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_EMAIL, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Email")
    }

    fun getValueStringNopeg(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NOPEG, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Nopeg")
    }

    fun getValuePassword(KEY_NAME: String):String? {
        val sharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_PASSWORD, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Password")
    }

    fun clear():Boolean{
        return try {
            val sharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_TOKEN, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            true
        } catch (e: Exception){
            false
        }
    }
}