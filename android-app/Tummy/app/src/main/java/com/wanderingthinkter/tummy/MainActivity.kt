
package com.wanderingthinkter.tummy

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.wanderingthinkter.tummy.api.RestApi
import com.wanderingthinkter.tummy.api.Routes
import com.wanderingthinkter.tummy.appfactorylib.CredentialHelper
import com.wanderingthinkter.tummy.entities.LoginDoc
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar?.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContentView(R.layout.activity_main)

        val restApi = RestApi(this)
        val gson = Gson()

        signUpBtn.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = passwordTxt.text.toString()
            val loginDoc = JSONObject(gson.toJson(LoginDoc(email, password)))

            val loginSuccess = Response.Listener<JSONObject> { response ->
                var credentials = CredentialHelper(this)
                val headers = response.get("headers") as Map<String, String>
                credentials.email = email
                credentials.password = password
                credentials.token = headers.get("Authorization")
                println("credentials: $credentials")
                loadingWid.visibility = View.INVISIBLE
                loginButton.setBackgroundColor(getColor(R.color.colorPrimary))
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            }

            val loginError = Response.ErrorListener { error ->
                var msg = getString(R.string.server_busy)
                println("error: $error")
                if(error.networkResponse.statusCode === 403) {
                    msg = getString(R.string.unauthorizeduser)
                }
                loadingWid.visibility = View.INVISIBLE
                loginButton.setBackgroundColor(getColor(R.color.colorPrimary))
                val snackBar = Snackbar.make(it, msg, Snackbar.LENGTH_LONG)
                snackBar.show()
            }

            restApi.request(Request.Method.POST, Routes.LOGIN_URL, loginDoc, loginSuccess, loginError)
            loadingWid.visibility = View.VISIBLE
            loginButton.setBackgroundColor(getColor(R.color.disabled))
        }
    }
}
