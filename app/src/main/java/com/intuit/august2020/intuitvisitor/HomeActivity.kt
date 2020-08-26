package com.intuit.august2020.intuitvisitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                //TODO: Set a false in SharedPrefs
                finish()
            }
            R.id.action_print -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}