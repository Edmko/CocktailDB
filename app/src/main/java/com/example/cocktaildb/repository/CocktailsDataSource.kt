package com.example.cocktaildb.repository

import androidx.paging.DataSource
import com.example.cocktaildb.data.entity.DatabaseDrink

interface CocktailsDataSource {

    suspend fun insertDrinks(drinks: List<DatabaseDrink>)
    fun observeDrinks(): DataSource.Factory<Int, DatabaseDrink>
    fun observeDrinksByType(filter: MutableSet<String>): DataSource.Factory<Int, DatabaseDrink>
}