package com.example.cocktaildb.repository

import com.example.cocktaildb.CocktailApp.Companion.appDatabase
import com.example.cocktaildb.data.CocktailApiObj
import com.example.cocktaildb.data.entity.asDatabaseDrink
import io.reactivex.schedulers.Schedulers

class ServerCommunicator {

    suspend fun loadFiltersList() = CocktailApiObj.retrofitService.getFilters("list")
    suspend fun getDrinksByFilter(filter: String) = CocktailApiObj.retrofitService.getDrinksByFilterSuspend(filter)

    fun loadCocktailsListToData(filters: List<String>?) {

        filters?.forEach { filter ->
            CocktailApiObj.retrofitService.getDrinksByFilter(filter)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {drinkList->   appDatabase.drinkDao.insertDrinks(drinkList.drinks.asDatabaseDrink(filter) ) }
        }
    }
}