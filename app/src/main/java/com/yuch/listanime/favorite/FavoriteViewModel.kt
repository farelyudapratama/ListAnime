package com.yuch.listanime.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.yuch.listanime.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(animeUseCase: AnimeUseCase) : ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().toLiveData()
}