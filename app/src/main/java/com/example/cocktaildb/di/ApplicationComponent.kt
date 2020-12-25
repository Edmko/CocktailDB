package com.example.cocktaildb.di

import com.example.cocktaildb.CocktailApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CocktailApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CocktailApp>()
}