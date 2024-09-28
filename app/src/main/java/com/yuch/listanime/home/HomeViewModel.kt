package com.yuch.listanime.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.yuch.listanime.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(animeUseCase: AnimeUseCase) : ViewModel() {
    val anime = animeUseCase.getTopAnime().toLiveData()
}