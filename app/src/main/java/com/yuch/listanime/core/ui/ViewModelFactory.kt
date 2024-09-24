package com.yuch.listanime.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuch.listanime.core.di.Injection
import com.yuch.listanime.core.domain.usecase.AnimeUseCase
import com.yuch.listanime.detail.DetailAnimeViewModel
import com.yuch.listanime.favorite.FavoriteViewModel
import com.yuch.listanime.home.HomeViewModel

class ViewModelFactory private constructor(private val animeUseCase: AnimeUseCase): ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideUseCase(context)
                ).also { instance = it }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(animeUseCase) as T
            }
            modelClass.isAssignableFrom(DetailAnimeViewModel::class.java) -> {
                DetailAnimeViewModel(animeUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(animeUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}