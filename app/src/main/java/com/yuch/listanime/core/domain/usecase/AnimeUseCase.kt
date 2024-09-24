package com.yuch.listanime.core.domain.usecase

import androidx.lifecycle.LiveData
import com.yuch.listanime.core.data.Resource
import com.yuch.listanime.core.domain.model.Anime

interface AnimeUseCase {
    fun getTopAnime(): LiveData<Resource<List<Anime>>>
    fun getFavoriteAnime(): LiveData<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}