package com.example.flashcards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.Note
import com.example.flashcards.data.NoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NoteDao
): ViewModel() {
    val allNotes: Flow<List<Note>> = dao.getAllNotes()
    fun insert(note: Note) = viewModelScope.launch {
        dao.insert(note)
    }
    fun update(note: Note) = viewModelScope.launch {
        dao.update(note)
    }
    fun delete(note: Note) = viewModelScope.launch {
        dao.delete(note)
    }
}
// boilerplate code for the ViewModelFactory
class NoteViewModelFactory(
    private val dao: NoteDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}