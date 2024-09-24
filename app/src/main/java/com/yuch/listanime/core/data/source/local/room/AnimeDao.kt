package com.yuch.listanime.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yuch.listanime.core.data.source.local.entity.AnimeEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime ORDER BY rank ASC")
    fun getTopAnime(): Flowable<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE isFavorite = 1")
    fun getFavoriteAnime(): Flowable<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(anime: List<AnimeEntity>): Completable

    @Update
    fun updateFavoriteAnime(anime: AnimeEntity)
}