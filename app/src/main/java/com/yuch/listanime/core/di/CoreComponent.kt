package com.yuch.listanime.core.di

import android.content.Context
import com.yuch.listanime.core.domain.repository.IAnimeRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }
    fun provideRepository() : IAnimeRepository
}