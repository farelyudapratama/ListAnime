package com.yuch.listanime.di

import com.yuch.listanime.core.di.CoreComponent
import com.yuch.listanime.detail.DetailAnimeActivity
import com.yuch.listanime.favorite.FavoriteFragment
import com.yuch.listanime.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailAnimeActivity)
}