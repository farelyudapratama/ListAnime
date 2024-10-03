package com.yuch.listanime.core.utils

import com.yuch.listanime.core.data.source.local.entity.AnimeEntity
import com.yuch.listanime.core.data.source.remote.response.AnimeResponse
import com.yuch.listanime.core.domain.model.Anime

object DataMapper {
    fun mapResponsesToEntities(input: List<AnimeResponse>): List<AnimeEntity> {
        val animeList = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                malId = it.malId,
                title = it.title,
                titleJapanese = it.titleJapanese,
                images = it.images?.webp?.largeImageUrl,
                type = it.type,
                source = it.source,
                episodes = it.episodes,
                status = it.status,
                airedFrom = it.aired?.from,
                airedTo = it.aired?.to,
                duration = it.duration,
                rating = it.rating,
                score = it.score,
                rank = it.rank,
                synopsis = it.synopsis,
                background = it.background,
                season = it.season,
                year = it.year,
                popularity = it.popularity,
                studio = it.studios?.firstOrNull()?.name,
                members = it.members,
                genre = it.genres?.firstOrNull()?.name,
                theme = it.themes?.firstOrNull()?.name,
                demographics = it.demographics?.firstOrNull()?.name,
                isFavorite = false
            )
            animeList.add(anime)
        }
        return animeList
    }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> =
        input.map {
            Anime(
                malId = it.malId,
                title = it.title,
                titleJapanese = it.titleJapanese,
                imageUrl = it.images,
                type = it.type,
                source = it.source,
                episodes = it.episodes,
                status = it.status,
                airedFrom = it.airedFrom,
                airedTo = it.airedTo,
                duration = it.duration,
                rating = it.rating,
                score = it.score,
                rank = it.rank,
                synopsis = it.synopsis,
                background = it.background,
                season = it.season,
                year = it.year,
                popularity = it.popularity,
                studio = it.studio,
                members = it.members,
                genre = it.genre,
                theme = it.theme,
                demographics = it.demographics,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Anime) = AnimeEntity(
        malId = input.malId,
        title = input.title,
        titleJapanese = input.titleJapanese,
        images = input.imageUrl,
        type = input.type,
        source = input.source,
        episodes = input.episodes,
        status = input.status,
        airedFrom = input.airedFrom,
        airedTo = input.airedTo,
        duration = input.duration,
        rating = input.rating,
        score = input.score,
        rank = input.rank,
        synopsis = input.synopsis,
        background = input.background,
        season = input.season,
        year = input.year,
        popularity = input.popularity,
        studio = input.studio,
        members = input.members,
        genre = input.genre,
        theme = input.theme,
        demographics = input.demographics,
        isFavorite = input.isFavorite
    )
}