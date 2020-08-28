package com.intuit.august2020.intuitvisitor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.edit
import com.intuit.august2020.intuitvisitor.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnVisitor.setOnClickListener {
            startActivity(Intent(this, VisitorActivity::class.java))
        }

        btnNotes.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }


        btnInfo.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                val prefs = getSharedPreferences("login-data", Context.MODE_PRIVATE)
                prefs.edit {
                    putBoolean(LoginActivity.KEY_IS_LOGGED_IN, false)
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                }
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}