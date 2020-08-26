package com.intuit.august2020.intuitvisitor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_NEW_NOTE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            // Add Section
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, 100)
            //TODO: Move 100 to a const val in a companion object

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==100) {
            if (requestCode== Activity.RESULT_OK) {
                val note = data?.getStringExtra("note")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}