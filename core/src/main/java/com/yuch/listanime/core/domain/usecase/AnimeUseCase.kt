package com.yuch.listanime.core.domain.usecase

import com.yuch.listanime.core.Resource
import com.yuch.listanime.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getTopAnime(): Flow<Resource<List<Anime>>>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}