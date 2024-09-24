package com.yuch.listanime.detail

import androidx.lifecycle.ViewModel
import com.yuch.listanime.core.domain.model.Anime
import com.yuch.listanime.core.domain.usecase.AnimeUseCase

class DetailAnimeViewModel(private val animeUseCase: AnimeUseCase) : ViewModel() {
    fun setFavoriteAnime(anime: Anime, newStatus:Boolean) =
        animeUseCase.setFavoriteAnime(anime, newStatus)
}