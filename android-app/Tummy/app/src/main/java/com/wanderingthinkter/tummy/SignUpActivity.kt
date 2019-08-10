package com.wanderingthinkter.tummy

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.wanderingthinkter.tummy.api.RestApi
import com.wanderingthinkter.tummy.api.Routes
import com.wanderingthinkter.tummy.appconstants.CustomDataTypes
import com.wanderingthinkter.tummy.entities.TummyUser
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private val date = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        updateDob()
    }

    private fun updateDob() {
        val dateFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(dateFormat)
        val dateString = sdf.format(calendar.time)
        dobInput.setText(dateString)
    }

    private fun vibrate() {
        var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(500)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar?.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContentView(R.layout.activity_sign_up)

        val restApi = RestApi(this)
        val gson = Gson()

        dobInput.setOnClickListener {
            DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        confirmBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val pwd = pwdInput.text.toString()
            val firstName = firstNameInput.text.toString()
            val lastName = lastNameInput.text.toString()
            val nationality = nationalityInput.text.toString()
            val dob = dobInput.text.toString()

            val user = TummyUser(email, firstName, lastName, pwd, setOf(CustomDataTypes.Roles.USER), dob, nationality)

            if(!user.isMandatoryFieldsPresent()) {
                val snackbar = Snackbar.make(it, getString(R.string.mandatory_fields), Snackbar.LENGTH_LONG)
                snackbar.show()
                vibrate()
                return@setOnClickListener
            } else if(!user.isPasswordsMatching(pwdConfirmInput.text.toString())) {
                val snackbar = Snackbar.make(it, getString(R.string.pwd_not_matching), Snackbar.LENGTH_LONG)
                snackbar.show()
                vibrate()
                return@setOnClickListener
            }

            val body = JSONObject(gson.toJson(user))

            val signUpListener = Response.Listener<JSONObject> { response ->
                val snackBar = Snackbar.make(it, response.getString("message"), Snackbar.LENGTH_LONG)
                snackBar.show()
                confirmBtn.text = getString(R.string.confirm)
                confirmBtn.setBackgroundColor(getColor(R.color.colorPrimary))
            }

            val signUpErrorListener = Response.ErrorListener { error ->
                val snackBar = Snackbar.make(it, getString(R.string.server_busy), Snackbar.LENGTH_LONG)
                snackBar.show()
                confirmBtn.text = getString(R.string.confirm)
                confirmBtn.setBackgroundColor(getColor(R.color.colorPrimary))
            }

            restApi.request(Request.Method.POST, Routes.SIGNUP_URL, body, signUpListener, signUpErrorListener)
            confirmBtn.text = getString(R.string.loading)
            confirmBtn.setBackgroundColor(getColor(R.color.disabled))
        }

    }

}
