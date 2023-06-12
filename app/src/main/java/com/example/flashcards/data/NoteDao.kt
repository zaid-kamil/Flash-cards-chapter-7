package com.example.flashcards.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>
    @Insert
    fun insert(note: Note)
    @Update
    fun update(note: Note)
    @Delete
    fun delete(note: Note)
}
