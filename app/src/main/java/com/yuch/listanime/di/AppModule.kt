package com.yuch.listanime.di

import com.yuch.listanime.core.domain.usecase.AnimeInteractor
import com.yuch.listanime.core.domain.usecase.AnimeUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideAnimeUseCase(animeInteractor: AnimeInteractor): AnimeUseCase

}