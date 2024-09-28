package com.yuch.listanime

import android.app.Application
import com.yuch.listanime.core.di.CoreComponent
import com.yuch.listanime.core.di.DaggerCoreComponent
import com.yuch.listanime.di.AppComponent
import com.yuch.listanime.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}