package com.yuch.listanime.core.di

import android.content.Context
import androidx.room.Room
import com.yuch.listanime.core.data.source.local.room.AnimeDao
import com.yuch.listanime.core.data.source.local.room.AnimeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AnimeDatabase = Room.databaseBuilder(
        context,
        AnimeDatabase::class.java, "Anime.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideAnimeDao(database: AnimeDatabase): AnimeDao = database.animeDao()
}