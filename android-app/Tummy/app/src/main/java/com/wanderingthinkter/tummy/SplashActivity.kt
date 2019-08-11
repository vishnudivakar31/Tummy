package com.wanderingthinkter.tummy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.wanderingthinkter.tummy.api.RestApi
import com.wanderingthinkter.tummy.api.Routes
import com.wanderingthinkter.tummy.appfactorylib.CredentialHelper
import com.wanderingthinkter.tummy.entities.LoginDoc
import org.json.JSONObject

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sleepTime = 3000
        try {
            this.supportActionBar?.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContentView(R.layout.activity_splash)

        val restApi = RestApi(this)
        val gson = Gson()

        var credentials = CredentialHelper(this)

        val loginSuccess = Response.Listener<JSONObject> { response ->
            var credentials = CredentialHelper(this)
            val headers = response.get("headers") as Map<String, String>
            credentials.token = headers.get("Authorization")
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }

        val loginError = Response.ErrorListener { error ->
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        if(!credentials.email.isNullOrBlank() && !credentials.password.isNullOrBlank()) {
            val loginDoc = JSONObject(gson.toJson(LoginDoc(credentials.email!!, credentials.password!!)))
            restApi.request(Request.Method.POST, Routes.LOGIN_URL, loginDoc, loginSuccess, loginError)
        } else {
            val background = object : Thread() {
                override fun run() {
                    try {
                        sleep(3000)
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                    } catch(e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            background.start()
        }
    }
}
