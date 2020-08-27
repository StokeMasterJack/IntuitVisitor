package com.intuit.august2020.intuitvisitor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.content_notes.*

class NotesActivity : AppCompatActivity() {

    val notes = mutableListOf<String>()
    lateinit var adapter: NotesAdapter //lateinit let us leave the var without a value now

    companion object {
        const val REQUEST_NEW_NOTE = 100
    }

    fun newNote() {
        notes.add("New note")
        // This is to notify the dataset that there is new data
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        for (i in 0..100) {
            notes.add("Note $i")
        }

        setSupportActionBar(toolbar)

        // Set up our RecyclerView
        recyclerNotes.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        val adapter = NotesAdapter(notes)
        // We detect when the user taps on one item in the RecyclerView
        adapter.setOnItemSelected { selectionIndex ->
            Log.d("Recycler", "$selectionIndex was clicked")
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
            if (requestCode== Activity.RESULT_OK) {
                val note = data?.getStringExtra("note")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}