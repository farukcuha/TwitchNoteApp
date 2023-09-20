package com.farukcuha.twitchnoteapp.data.repository.di

import com.farukcuha.twitchnoteapp.data.local.NotesDao
import com.farukcuha.twitchnoteapp.data.repository.NotesRepositoryImpl
import com.farukcuha.twitchnoteapp.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNotesRepository(
        dao: NotesDao
    ): NotesRepository {
        return NotesRepositoryImpl(dao)
    }
}