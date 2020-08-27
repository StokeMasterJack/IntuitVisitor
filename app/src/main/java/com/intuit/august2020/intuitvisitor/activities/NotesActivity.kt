package com.intuit.august2020.intuitvisitor.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import com.intuit.august2020.intuitvisitor.adapters.NotesAdapter
import com.intuit.august2020.intuitvisitor.R
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.content_notes.*
import org.json.JSONArray

class NotesActivity : AppCompatActivity() {

    val notes = mutableListOf<String>()
    lateinit var adapter: NotesAdapter //lateinit let us leave the var without a value now

    companion object {
        const val REQUEST_NEW_NOTE = 100
        const val KEY_NOTES = "notes"
    }

    fun newNote(note: String) {
        notes.add(note)
        // This is to notify the dataset that there is new data
        adapter.notifyDataSetChanged()

        // Save the data
        val prefs = getSharedPreferences("login-data", Context.MODE_PRIVATE)
        prefs.edit {
            val jsonArray = JSONArray(notes)
            putString(KEY_NOTES, jsonArray.toString())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

//        // Incoming
//        intent.getStringExtra("note")

        val prefs = getSharedPreferences("login-data", Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_NOTES, "[]")
        var notesJson = JSONArray(json)
        for (i in 0 until notesJson.length()) {
            notes.add(notesJson[i] as String)
        }


        setSupportActionBar(toolbar)

        // Set up our RecyclerView
        recyclerNotes.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        adapter =
            NotesAdapter(notes)
        // We detect when the user taps on one item in the RecyclerView
        adapter.setOnItemSelected { selectionIndex ->
            Log.d("Recycler", "$selectionIndex was clicked")
            val intent = Intent(this, NewNoteActivity::class.java)
            intent.putExtra("note", notes[selectionIndex])
            startActivity(intent)
        }
        recyclerNotes.adapter = adapter


        // Floating Action Button
        fab.setOnClickListener { view ->
            // Add Section
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, 100)
            //TODO: Move 100 to a const val in a companion object

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==100) {
            if (resultCode== Activity.RESULT_OK) {
                val note = data?.getStringExtra("note") ?: "No text"
                newNote(note)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}