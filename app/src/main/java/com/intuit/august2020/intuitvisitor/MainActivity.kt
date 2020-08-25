package com.intuit.august2020.intuitvisitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    fun onClick(name: String, lambda: (String)->Unit) {
        lambda("hello")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClick("This is the name") {
            println(it)
        }

        button_login.setOnClickListener {
            val user = edit_user_name.text.toString()
            val pass = edit_password.text.toString()

            API.login(user, pass) { isLoggedIn ->
                if (!isLoggedIn) {
                    Toast.makeText(this,
                        if (isLoggedIn) "You are OK" else "Credentials are wrong",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}