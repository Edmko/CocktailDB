package com.example.cocktaildb.di

import android.content.Context
import com.ashokvarma.gander.GanderInterceptor
import com.example.cocktaildb.BuildConfig
import com.example.cocktaildb.CocktailApp
import com.example.cocktaildb.data.CocktailApi
import com.example.cocktaildb.data.CocktailApiFactory
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {

    @Provides
    fun provideContext(app: CocktailApp): Context {
        return app.applicationContext
    }

    @Provides
    fun provideApiClient(
        ganderInterceptor: GanderInterceptor
    ): CocktailApi {
        return CocktailApiFactory.newInstance(BuildConfig.ENDPOINT, ganderInterceptor)
    }

    @Provides
    fun provideGanderInterceptor(app: CocktailApp): GanderInterceptor {
        return GanderInterceptor(app.applicationContext).showNotification(true)
    }
}