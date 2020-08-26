package com.intuit.august2020.intuitvisitor

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        //TODO: Save  button
        val dataIntent = Intent()
        dataIntent.putExtra("note", "This is a new note")
        setResult(Activity.RESULT_OK, dataIntent)

        NotesActivity.REQUEST_NEW_NOTE
    }
}