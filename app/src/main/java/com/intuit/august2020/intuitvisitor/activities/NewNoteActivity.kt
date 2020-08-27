package com.intuit.august2020.intuitvisitor.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intuit.august2020.intuitvisitor.R
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        buttonSave.setOnClickListener {
            val dataIntent = Intent()
            dataIntent.putExtra("note", textNewNote.text.toString())
            setResult(Activity.RESULT_OK, dataIntent)
            finish() // we should close our Activity to go back
        }
    }
}