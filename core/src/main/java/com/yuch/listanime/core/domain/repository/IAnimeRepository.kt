package com.yuch.listanime.core.domain.repository

import com.yuch.listanime.core.Resource
import com.yuch.listanime.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getTopAnime(): Flow<com.yuch.listanime.core.Resource<List<Anime>>>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}