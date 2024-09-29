package com.yuch.listanime.core.data

import com.yuch.listanime.core.data.source.local.LocalDataSource
import com.yuch.listanime.core.data.source.remote.RemoteDataSource
import com.yuch.listanime.core.data.source.remote.network.ApiResponse
import com.yuch.listanime.core.data.source.remote.response.AnimeResponse
import com.yuch.listanime.core.domain.model.Anime
import com.yuch.listanime.core.domain.repository.IAnimeRepository
import com.yuch.listanime.core.utils.AppExecutors
import com.yuch.listanime.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IAnimeRepository {

        companion object {
        @Volatile
        private var instance: AnimeRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): AnimeRepository = instance ?: synchronized(this) {
            instance ?: AnimeRepository(remoteData, localData, appExecutors)
        }
    }

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
                localDataSource.insertAnime(animeList)
            }

            override fun shouldFetch(data: List<Anime>?): Boolean =
                true
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