package com.yuch.listanime.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val malId: Int?,
    val title: String?,
    val titleJapanese: String?,
    val imageUrl: String?,
    val type: String?,
    val source: String?,
    val episodes: Int?,
    val status: String?,
    val airedFrom: String?,
    val airedTo: String?,
    val duration: String?,
    val rating: String?,
    val score: Double?,
    val rank: Int?,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val popularity: Int?,
    val studio: String?,
    val members: Int?,
    val genre: String?,
    val theme: String?,
    val demographics: String?,
    var isFavorite: Boolean = false
): Parcelable