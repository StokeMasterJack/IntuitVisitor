package com.intuit.august2020.intuitvisitor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    val cities = listOf("Palo Alto", "Shanghai", "Casablanca", "Madrid", "Montreal")

    fun onClick(name: String, lambda: (String)->Unit) {
        lambda("hello")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //TODO: Check if the user is auto logged in
        // Start HomeActivity

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter = adapter
        spinnerCity.setSelection(3)


        button_login.setOnClickListener {
            val user = edit_user_name.text.toString()
            val pass = edit_password.text.toString()

            API.login(user, pass) { isLoggedIn ->
                Toast.makeText(this,
                    if (isLoggedIn) "You are OK" else "Credentials are wrong",
                    Toast.LENGTH_LONG).show()

                if (isLoggedIn) {
                    // Save flag for next sessions
                    val prefs = getSharedPreferences("login-data", Context.MODE_PRIVATE)
                    prefs.edit {
                        // Transaction
                        putBoolean(KEY_IS_LOGGED_IN, true)
                    }

                    // Move to the next activity
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }


            }
        }
    }


}