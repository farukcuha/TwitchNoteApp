package com.farukcuha.twitchnoteapp.data.local.di

import android.content.Context
import androidx.room.Room
import com.farukcuha.twitchnoteapp.data.local.NotesDao
import com.farukcuha.twitchnoteapp.data.local.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideNotesDao(
        notesDatabase: NotesDatabase
    ): NotesDao {
        return notesDatabase.notesDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes-database"
        ).allowMainThreadQueries().build()
    }
}