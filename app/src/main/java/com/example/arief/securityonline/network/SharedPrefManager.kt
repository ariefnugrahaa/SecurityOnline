package com.example.arief.securityonline.network

import android.content.Context

class SharedPrefManager private constructor(private val mCtx: Context){

    companion object{
        private val SHARED_PREF_NAME = "my_shared_pref"
        private val SHARED_PREF_USER = "my_shared_user"
        private val SHARED_PREF_JABATAN = "my_shared_jabatan"
        private val SHARED_PREF_PASSWORD = "my_shared_password"
        private val SHARED_PREF_ID = "my_shared_id"

        private var mInstance:SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager{
            if (mInstance == null){
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

    fun isLoggedIn(): Boolean {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString("tokenAkses", null) != null
    }

    fun saveToken(token: String){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("tokenAkses", token)
        editor.apply()
    }

    fun saveUser(user: String){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_USER, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Username", user)
        editor.apply()
    }

    fun saveNopeg(jabatan: String){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_JABATAN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Jabatan", jabatan)
        editor.apply()
    }


    fun saveId(id:String){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("id", id)
        editor.apply()
    }

    fun savePassword(password: String){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_PASSWORD, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Password", password)
        editor.apply()
    }


    fun getValueId(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "id")
    }


    fun getValueToken(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "tokenAkses")
    }

    fun getValueUser(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_USER, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Username")
    }

    fun getValueStringNopeg(KEY_NAME: String): String? {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_JABATAN, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Jabatan")
    }

    fun getValuePassword(KEY_NAME: String):String? {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_PASSWORD, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "Password")
    }

    fun clear():Boolean{
        return try {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            true
        } catch (e: Exception){
            false
        }
    }
}