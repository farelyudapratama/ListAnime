package com.yuch.listanime.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yuch.listanime.core.domain.usecase.AnimeUseCase

class FavoriteViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}