package com.yuch.listanime.core.di

import android.content.Context
import com.yuch.listanime.core.data.AnimeRepository
import com.yuch.listanime.core.data.source.local.room.AnimeDatabase
import com.yuch.listanime.core.data.source.remote.RemoteDataSource
import com.yuch.listanime.core.data.source.remote.network.ApiConfig
import com.yuch.listanime.core.domain.repository.IAnimeRepository
import com.yuch.listanime.core.domain.usecase.AnimeInteractor
import com.yuch.listanime.core.domain.usecase.AnimeUseCase
import com.yuch.listanime.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IAnimeRepository{
        val database = AnimeDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = com.yuch.listanime.core.data.source.local.LocalDataSource.getInstance(database.animeDao())
        val appExecutors = AppExecutors()

        return AnimeRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideUseCase(context: Context): AnimeUseCase {
        val repository = provideRepository(context)
        return AnimeInteractor(repository)
    }
}