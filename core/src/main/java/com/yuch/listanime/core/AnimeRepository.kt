package com.yuch.listanime.core

import com.yuch.listanime.core.data.source.local.LocalDataSource
import com.yuch.listanime.core.domain.model.Anime
import com.yuch.listanime.core.domain.repository.IAnimeRepository
import com.yuch.listanime.core.data.source.remote.RemoteDataSource
import com.yuch.listanime.core.data.source.remote.network.ApiResponse
import com.yuch.listanime.core.data.source.remote.response.AnimeResponse
import com.yuch.listanime.core.utils.AppExecutors
import com.yuch.listanime.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AnimeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IAnimeRepository {

    override fun getTopAnime(): Flow<Resource<List<Anime>>> =
        object : NetworkBoundResource<List<Anime>, List<AnimeResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getTopAnime().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> =
                remoteDataSource.getTopAnime()

            override suspend fun saveCallResult(data: List<AnimeResponse>) {
                val animeList = DataMapper.mapResponsesToEntities(data)
                val currentFavorites = localDataSource.getFavoriteAnime().first()

                val updatedAnimeList = animeList.map { animeEntity ->
                    val isFavorite = currentFavorites.find { it.malId == animeEntity.malId }?.isFavorite ?: false
                    animeEntity.isFavorite = isFavorite
                    animeEntity
                }
                localDataSource.insertAnime(updatedAnimeList)
            }

            override fun shouldFetch(data: List<Anime>?): Boolean = true
        }.asFlow()

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnime().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAnime(anime: Anime, state: Boolean) {
        val animeEntity = DataMapper.mapDomainToEntity(anime)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAnime(animeEntity, state) }
    }

}