package com.farukcuha.twitchnoteapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesScreenViewModel: ViewModel() {
    var notes: List<NoteEntity> = mutableStateListOf()

    init {
        viewModelScope.launch {
            NotesApplication.db?.notesDao()?.getNotes()?.collect {
                notes = it
            }
        }
    }
}