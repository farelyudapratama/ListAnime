package com.yuch.listanime.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("data")
	val data: List<AnimeResponse?>? = null
)

