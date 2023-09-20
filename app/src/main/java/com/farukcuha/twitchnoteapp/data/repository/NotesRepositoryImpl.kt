package com.farukcuha.twitchnoteapp.data.repository

import com.farukcuha.twitchnoteapp.data.local.NotesDao
import com.farukcuha.twitchnoteapp.data.model.entity.NoteEntity
import com.farukcuha.twitchnoteapp.domain.model.Note
import com.farukcuha.twitchnoteapp.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val dao: NotesDao
): NotesRepository {
    override suspend fun insertNote(title: String?, body: String?) {
        val noteEntity = NoteEntity(
            title = title,
            body = body,
            time = System.currentTimeMillis()
        )
        return dao.insertNote(noteEntity)
    }

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { list ->
            list.map { it.toNote() }
        }.flowOn(Dispatchers.IO)
    }

    override fun getNote(noteId: Int?): Note? {
        if (noteId == null) return null
        return dao.getNote(noteId).toNote()
    }

    override suspend fun updateNote(noteId: Int?, title: String?, body: String?) {
        val noteEntity = NoteEntity(
            id = noteId,
            title = title,
            body = body,
            time = System.currentTimeMillis()
        )
        return dao.updateNote(noteEntity)
    }

    override suspend fun deleteNote(noteId: Int?) {
        if (noteId == null) return
        return dao.deleteNote(noteId)
    }

    override suspend fun clear() {
        return dao.clear()
    }
}