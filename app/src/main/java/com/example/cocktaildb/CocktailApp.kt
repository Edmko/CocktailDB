package com.example.cocktaildb

import androidx.appcompat.app.AppCompatDelegate
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.imdb.GanderIMDB
import com.example.cocktaildb.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasAndroidInjector

open class CocktailApp : DaggerApplication(), HasAndroidInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        Gander.setGanderStorage(GanderIMDB.getInstance())
    }
}