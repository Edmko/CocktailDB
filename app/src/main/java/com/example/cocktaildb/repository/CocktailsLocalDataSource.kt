package com.example.cocktaildb.repository

import android.util.Log
import androidx.paging.DataSource
import com.example.cocktaildb.CocktailApp
import com.example.cocktaildb.data.dao.DrinkDao
import com.example.cocktaildb.data.entity.DatabaseDrink
import com.example.cocktaildb.data.entity.Drink
import com.example.cocktaildb.data.entity.asDatabaseDrink
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class CocktailsLocalDataSource  (

) : CocktailsDataSource
{
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val drinkDao = CocktailApp.appDatabase.drinkDao

    override suspend fun insertDrinks(drinks: List<DatabaseDrink>) = withContext(ioDispatcher) {
        drinkDao.insertDrinks(drinks)
    }

    fun getDrinksByType(filter: List<String>) : DataSource.Factory<Int, DatabaseDrink>{
        return drinkDao.getDrinksByType(filter.sorted())
    }
    override fun observeDrinks(): DataSource.Factory<Int, DatabaseDrink> {
        return drinkDao.getDrinks()
    }
}