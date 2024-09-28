package com.yuch.listanime.di

import com.yuch.listanime.core.domain.usecase.AnimeInteractor
import com.yuch.listanime.core.domain.usecase.AnimeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideAnimeUseCase(animeInteractor: AnimeInteractor): AnimeUseCase

}