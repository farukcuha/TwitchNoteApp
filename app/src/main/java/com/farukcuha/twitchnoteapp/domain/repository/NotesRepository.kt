package com.farukcuha.twitchnoteapp.domain.repository

import com.farukcuha.twitchnoteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insertNote(title: String?, body: String?)

    fun getNotes(): Flow<List<Note>>

    fun getNote(noteId: Int?): Note?

    suspend fun updateNote(noteId: Int?, title: String?, body: String?)

    suspend fun deleteNote(noteId: Int?)

    suspend fun clear()
}