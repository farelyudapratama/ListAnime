package com.yuch.listanime.core.data.source.remote

import android.util.Log
import com.yuch.listanime.core.data.source.remote.network.ApiResponse
import com.yuch.listanime.core.data.source.remote.network.ApiService
import com.yuch.listanime.core.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){

    suspend fun getTopAnime(): Flow<ApiResponse<List<AnimeResponse>>> {
        return flow {
            try {
                val response = apiService.getTopAnime()
                val dataArray = response.data?.filterNotNull()

                if (!dataArray.isNullOrEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Error: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

}