package com.intuit.august2020.intuitvisitor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intuit.august2020.intuitvisitor.R
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        textNote.text = intent.getStringExtra("note")
    }
}