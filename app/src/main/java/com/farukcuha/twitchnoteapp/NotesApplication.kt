package com.farukcuha.twitchnoteapp

import android.app.Application
import androidx.room.Room
import com.farukcuha.twitchnoteapp.data.local.NotesDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApplication : Application() {

    companion object {
        var db: NotesDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java, "notes-database"
        ).allowMainThreadQueries().build()
    }
}