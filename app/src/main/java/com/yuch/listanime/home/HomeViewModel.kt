package com.yuch.listanime.home

import androidx.lifecycle.ViewModel
import com.yuch.listanime.core.domain.usecase.AnimeUseCase

class HomeViewModel(animeUseCase: AnimeUseCase) : ViewModel() {
    val anime = animeUseCase.getTopAnime()
}