package com.yuch.listanime.core.di

import com.yuch.listanime.core.data.AnimeRepository
import com.yuch.listanime.core.domain.repository.IAnimeRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(animeRepository: AnimeRepository): IAnimeRepository
}