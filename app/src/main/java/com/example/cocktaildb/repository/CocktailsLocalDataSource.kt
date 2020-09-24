package com.example.cocktaildb.repository

import androidx.paging.DataSource
import com.example.cocktaildb.CocktailApp
import com.example.cocktaildb.data.entity.DatabaseDrink
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailsLocalDataSource : CocktailsDataSource
{
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val drinkDao = CocktailApp.appDatabase.drinkDao

    override suspend fun insertDrinks(drinks: List<DatabaseDrink>) = withContext(ioDispatcher) {
        drinkDao.insertDrinks(drinks)
    }

    override fun observeDrinksByType(filter: MutableSet<String>) : DataSource.Factory<Int, DatabaseDrink>{
        return drinkDao.observeDrinksByType(filter)
    }
    override fun observeDrinks(): DataSource.Factory<Int, DatabaseDrink> {
        return drinkDao.getDrinks()
    }
}