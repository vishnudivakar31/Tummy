package com.wanderingthinkter.tummy.appfactorylib

import android.content.Context
import com.wanderingthinkter.tummy.R

class CredentialHelper(val ctx: Context) {
    val PREF_NAME = ctx.getString(R.string.preference_name)
    val EMAIL_NAME = ctx.getString(R.string.email)
    val PASSWORD_NAME = ctx.getString(R.string.password)
    val JWT_TOKEN = ctx.getString(R.string.jwt_token)
    val prefs = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var email: String?
        get() = prefs.getString(EMAIL_NAME, "")
        set(value) = prefs.edit().putString(EMAIL_NAME, value).apply()

    var password: String?
        get() = prefs.getString(PASSWORD_NAME, "")
        set(value) = prefs.edit().putString(PASSWORD_NAME, value).apply()

    var token: String?
        get() = prefs.getString(JWT_TOKEN, "")
        set(value) = prefs.edit().putString(JWT_TOKEN, value).apply()

    override fun toString(): String {
        return "email: $email, password: $password and token: $token"
    }
}