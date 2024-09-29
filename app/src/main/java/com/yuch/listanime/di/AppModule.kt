package com.yuch.listanime.di

import com.yuch.listanime.core.domain.usecase.AnimeInteractor
import com.yuch.listanime.core.domain.usecase.AnimeUseCase
import com.yuch.listanime.detail.DetailAnimeViewModel
import com.yuch.listanime.favorite.FavoriteViewModel
import com.yuch.listanime.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailAnimeViewModel(get()) }
}