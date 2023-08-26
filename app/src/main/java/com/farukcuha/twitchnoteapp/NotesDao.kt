package com.farukcuha.twitchnoteapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM NOTEENTITY ORDER BY time DESC")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id == :noteId LIMIT 1")
    fun getNote(noteId: Int): NoteEntity

    @Update
    suspend fun updateNote(noteEntity: NoteEntity?)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("DELETE FROM NOTEENTITY")
    suspend fun clear()
}