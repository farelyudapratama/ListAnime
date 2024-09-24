package com.yuch.listanime.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    val malId: Int?,
    val title: String?,
    val titleJapanese: String?,
    val images: String?,
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
)