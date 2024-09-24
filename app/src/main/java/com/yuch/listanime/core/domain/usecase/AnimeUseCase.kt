package com.yuch.listanime.core.domain.usecase

import com.yuch.listanime.core.data.Resource
import com.yuch.listanime.core.domain.model.Anime
import io.reactivex.rxjava3.core.Flowable

interface AnimeUseCase {
    fun getTopAnime(): Flowable<Resource<List<Anime>>>
    fun getFavoriteAnime(): Flowable<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}