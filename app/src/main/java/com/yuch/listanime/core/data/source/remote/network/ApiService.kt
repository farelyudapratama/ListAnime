package com.yuch.listanime.core.data.source.remote.network

import com.yuch.listanime.core.data.source.remote.response.TopAnimeResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET ("top/anime")
    fun getTopAnime(): Flowable<TopAnimeResponse>
}