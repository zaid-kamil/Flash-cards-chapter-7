package com.example.flashcards.data

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "content")
    val content: String
) {
    companion object {
        val colors = listOf(
            Color.parseColor("#FFCDD2"),
            Color.parseColor("#F8BBD0"),
            Color.parseColor("#E1BEE7"),
            Color.parseColor("#D1C4E9"),
            Color.parseColor("#C5CAE9"),
        )
    }
}
