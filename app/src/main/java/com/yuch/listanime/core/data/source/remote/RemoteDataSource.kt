package com.yuch.listanime.core.data.source.remote

import android.util.Log
import com.yuch.listanime.core.data.source.remote.network.ApiResponse
import com.yuch.listanime.core.data.source.remote.network.ApiService
import com.yuch.listanime.core.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object {
        @Volatile
        private var instace: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instace ?: synchronized(this) {
                instace ?: RemoteDataSource(service)
            }
    }

    suspend fun getTopAnime(): Flow<ApiResponse<List<AnimeResponse>>> {
        return flow {
            try {
                val response = apiService.getTopAnime()
                val dataArray = response.data?.filterNotNull()

                if (dataArray?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}