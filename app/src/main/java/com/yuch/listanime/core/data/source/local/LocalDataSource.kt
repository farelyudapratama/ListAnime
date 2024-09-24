package com.yuch.listanime.core.data.source.local

import com.yuch.listanime.core.data.source.local.entity.AnimeEntity
import com.yuch.listanime.core.data.source.local.room.AnimeDao
import io.reactivex.rxjava3.core.Flowable

class LocalDataSource private constructor(private val animeDao: AnimeDao){
    companion object {
        private var instance: LocalDataSource? = null
        fun getInstance(animeDao: AnimeDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(animeDao)
            }
    }
    fun getTopAnime(): Flowable<List<AnimeEntity>> = animeDao.getTopAnime()

    fun getFavoriteAnime(): Flowable<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    fun insertAnime(animeList: List<AnimeEntity>) = animeDao.insertAnime(animeList)

    fun setFavoriteAnime(anime: AnimeEntity, newState: Boolean) {
        anime.isFavorite = newState
        animeDao.updateFavoriteAnime(anime)
    }
}