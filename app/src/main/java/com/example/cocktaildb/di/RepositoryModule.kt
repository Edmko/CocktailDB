package com.example.cocktaildb.di

import com.example.cocktaildb.repository.CocktailRepository
import com.example.cocktaildb.repository.CocktailRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun  cocktailsRepository(repository: CocktailRepositoryImpl): CocktailRepository
}