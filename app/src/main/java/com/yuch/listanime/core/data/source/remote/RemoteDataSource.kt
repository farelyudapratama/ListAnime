package com.yuch.listanime.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuch.listanime.core.data.source.remote.network.ApiResponse
import com.yuch.listanime.core.data.source.remote.network.ApiService
import com.yuch.listanime.core.data.source.remote.response.AnimeResponse
import com.yuch.listanime.core.data.source.remote.response.ListAnimeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object {
        @Volatile
        private var instace: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instace ?: synchronized(this) {
                instace ?: RemoteDataSource(service)
            }
    }

    fun getTopAnime(): LiveData<ApiResponse<List<AnimeResponse>>>{
        val result = MutableLiveData<ApiResponse<List<AnimeResponse>>>()

        val client = apiService.getTopAnime()

        client.enqueue(object : Callback<ListAnimeResponse> {
            override fun onResponse(
                call: Call<ListAnimeResponse>,
                response: Response<ListAnimeResponse>
            ) {
                val dataArray = response.body()?.data?.filterNotNull()
                result.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListAnimeResponse>, t: Throwable) {
                result.value = ApiResponse.Error(t.message.toString())
            }
        })

        return result
    }
}