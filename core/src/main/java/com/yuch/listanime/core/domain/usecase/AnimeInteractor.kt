package com.yuch.listanime.core.domain.usecase

import androidx.lifecycle.LiveData
import com.yuch.listanime.core.Resource
import com.yuch.listanime.core.domain.model.Anime
import com.yuch.listanime.core.domain.repository.IAnimeRepository

class AnimeInteractor(private val animeRepository: IAnimeRepository): AnimeUseCase {
    override fun getTopAnime() = animeRepository.getTopAnime()
    override fun getFavoriteAnime() = animeRepository.getFavoriteAnime()
    override fun setFavoriteAnime(anime: Anime, state: Boolean) = animeRepository.setFavoriteAnime(anime, state)
}