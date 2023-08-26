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

    fun updateNoteByNoteId(noteId: Int, title: String, body: String) {
        val noteToUpdate = getNoteById(noteId)
        val updatedNote = noteToUpdate?.copy(
            title = title,
            body = body,
            time = System.currentTimeMillis()
        )
        viewModelScope.launch {
            NotesApplication.db?.notesDao()?.updateNote(updatedNote)
        }
    }

    fun getNoteById(noteId: Int): NoteEntity? {
        return NotesApplication.db?.notesDao()?.getNote(noteId)
    }
}