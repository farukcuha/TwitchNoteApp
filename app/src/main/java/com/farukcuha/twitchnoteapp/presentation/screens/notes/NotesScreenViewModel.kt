package com.farukcuha.twitchnoteapp.presentation.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farukcuha.twitchnoteapp.domain.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val repository: NotesRepository
): ViewModel() {
    var notes = repository.getNotes()

    fun clear() = viewModelScope.launch { repository.clear() }

    fun deleteNote(noteId: Int?) = viewModelScope.launch {
        repository.deleteNote(noteId)
    }
}