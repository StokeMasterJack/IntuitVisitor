package com.intuit.august2020.intuitvisitor.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intuit.august2020.intuitvisitor.R

class NotesAdapter(val notes: MutableList<String>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var count = 0
    var callback : ((Int)->Unit)? = null
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: String) {
            val textNote = itemView.findViewById<TextView>(R.id.text_note)
            textNote.text = note
        }
    }

    fun setOnItemSelected(itemSelectedCallback: (Int)->Unit) {
        callback = itemSelectedCallback
    }
    override fun getItemCount(): Int {
        return notes.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        count++
        Log.d("NotesAdapter", "Creating Note $count")
        val inflater = LayoutInflater.from(parent.context)
        // THIRD ARGUMENT MUST BE FALSE
        val itemView = inflater.inflate(R.layout.item_note, parent, false)
        val viewHolder =
            NotesViewHolder(
                itemView
            )
//
//        if (count % 2 == 0) {
//            itemView.setBackgroundColor(Color.BLUE)
//        } else {
//            itemView.setBackgroundColor(Color.BLACK)
//        }

        return viewHolder
    }
    override fun onBindViewHolder(viewHolder: NotesViewHolder, position: Int) {
        val note = notes[position]
        viewHolder.itemView.setOnClickListener {
            callback?.invoke(position)
        }
        viewHolder.bind(note)
    }
}