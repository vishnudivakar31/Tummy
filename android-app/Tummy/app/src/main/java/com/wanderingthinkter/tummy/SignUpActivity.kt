package com.wanderingthinkter.tummy

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
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

    private var localPwd = ""

    private fun updateDob() {
        val dateFormat = "dd/MM/yyyy"
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar?.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContentView(R.layout.activity_sign_up)

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

            if(email.isBlank() || pwd.isBlank() || firstName.isBlank() || lastName.isBlank()
                || nationality.isBlank() || dob.isBlank()) {
                val snackbar = Snackbar.make(it, getString(R.string.mandatory_fields), Snackbar.LENGTH_LONG)
                snackbar.show()
                vibrate()
                return@setOnClickListener
            } else if(pwd != pwdConfirmInput.text.toString()) {
                val snackbar = Snackbar.make(it, getString(R.string.pwd_not_matching), Snackbar.LENGTH_LONG)
                snackbar.show()
                vibrate()
                return@setOnClickListener
            }

            //Sign Up Call
        }

    }

}
