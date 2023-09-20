package com.farukcuha.twitchnoteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farukcuha.twitchnoteapp.data.model.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
}