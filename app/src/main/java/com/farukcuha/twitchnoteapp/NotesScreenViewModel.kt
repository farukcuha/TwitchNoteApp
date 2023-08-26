package com.farukcuha.twitchnoteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesScreenViewModel: ViewModel() {
    var notes = NotesApplication.db?.notesDao()?.getNotes()

    fun clear() = viewModelScope.launch {
        NotesApplication.db?.notesDao()?.clear()
    }

    fun deleteNote(noteEntity: NoteEntity) = viewModelScope.launch {
        NotesApplication.db?.notesDao()?.deleteNote(noteEntity)
    }
}