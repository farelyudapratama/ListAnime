package com.yuch.listanime.core.data.source.local

import com.yuch.listanime.core.data.source.local.entity.AnimeEntity
import com.yuch.listanime.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val animeDao: AnimeDao) {

    fun getTopAnime(): Flow<List<AnimeEntity>> = animeDao.getTopAnime()

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> {
        return animeDao.getFavoriteAnime()
    }

    suspend fun insertAnime(animeList: List<AnimeEntity>) = animeDao.insertAnime(animeList)

    fun setFavoriteAnime(anime: AnimeEntity, newState: Boolean) {
        anime.isFavorite = newState
        animeDao.updateFavoriteAnime(anime)
    }
}
