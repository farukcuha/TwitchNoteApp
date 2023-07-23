package com.farukcuha.twitchnoteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CreateNoteScreenViewModel : ViewModel() {

    fun createNote(title: String, body: String) {
        viewModelScope.launch {
            NotesApplication.db?.notesDao()?.insertNote(
                NoteEntity(title = title, body = body, time = System.currentTimeMillis())
            )
        }
    }
}