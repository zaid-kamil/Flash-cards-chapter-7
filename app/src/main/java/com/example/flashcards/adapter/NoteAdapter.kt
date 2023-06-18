package com.example.flashcards.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.data.Note
import com.example.flashcards.databinding.NoteCardLayoutBinding
import com.google.android.material.card.MaterialCardView

class NoteAdapter(private val onItemClicked: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffCallback) {

    class NoteViewHolder(private var binding: NoteCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(note: Note) {
            binding.apply {
                textTitle.text = note.title
                if (note.content.length > 300) {
                    textContent.text = note.content.take(300) + "..."
                } else
                    textContent.text = note.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(
            NoteCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }
}
