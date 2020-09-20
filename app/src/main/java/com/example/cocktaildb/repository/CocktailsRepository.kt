package com.example.cocktaildb.repository

import androidx.paging.DataSource
import com.example.cocktaildb.data.entity.DatabaseDrink

class CocktailsRepository {
    private val serverCommunicator = ServerCommunicator()
    private val cocktailsLocalDataSource = CocktailsLocalDataSource()

    fun saveDrinksToLocalDataSource(filters: List<String>) {
        serverCommunicator.loadCocktailsListToData(filters)

    }
    fun getDrinkByType(filter : List<String>): DataSource.Factory<Int, DatabaseDrink>{
        return cocktailsLocalDataSource.getDrinksByType(filter)
    }
    fun observeDrink(): DataSource.Factory<Int, DatabaseDrink> {
        return cocktailsLocalDataSource.observeDrinks()
    }

    fun loadFilterList() : MutableList<String>{
        return serverCommunicator.loadFiltersList()
    }
}