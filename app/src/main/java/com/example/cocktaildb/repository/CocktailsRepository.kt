package com.example.cocktaildb.repository

import androidx.paging.DataSource
import com.example.cocktaildb.data.entity.DatabaseDrink
import com.example.cocktaildb.data.entity.asDatabaseDrink

class CocktailsRepository {

    private val serverCommunicator = ServerCommunicator()
    private val cocktailsLocalDataSource = CocktailsLocalDataSource()

   suspend fun saveDrinksToLocalDataSource(filters: MutableSet<String>) {
        filters.forEach {filter ->
            val response = serverCommunicator.getDrinksByFilter(filter)
            cocktailsLocalDataSource.insertDrinks(response.drinks.asDatabaseDrink(filter))
            }
    }

    fun observeDrinksByFilters(filter: MutableSet<String>):DataSource.Factory<Int, DatabaseDrink> {
        return cocktailsLocalDataSource.observeDrinksByType(filter)
    }

    fun observeDrink(): DataSource.Factory<Int, DatabaseDrink> {
        return cocktailsLocalDataSource.observeDrinks()
    }

    suspend fun loadFilterList() = serverCommunicator.loadFiltersList()
}