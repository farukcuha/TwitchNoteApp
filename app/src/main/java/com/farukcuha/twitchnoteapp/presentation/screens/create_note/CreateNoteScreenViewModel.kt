package com.farukcuha.twitchnoteapp.presentation.screens.create_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farukcuha.twitchnoteapp.domain.model.Note
import com.farukcuha.twitchnoteapp.domain.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNoteScreenViewModel @Inject constructor(
    private val repository: NotesRepository
): ViewModel() {

    fun createNote(title: String, body: String) = viewModelScope.launch {
        repository.insertNote(title, body)
    }

    fun updateNoteByNoteId(noteId: Int, title: String, body: String) = viewModelScope.launch {
        repository.updateNote(noteId, title, body)
    }

    fun getNoteById(noteId: Int): Note? = repository.getNote(noteId)
}