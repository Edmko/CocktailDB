package com.example.cocktaildb.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.cocktaildb.CocktailApp.Companion.appDatabase
import com.example.cocktaildb.data.CocktailApiObj
import com.example.cocktaildb.data.entity.Filters
import com.example.cocktaildb.data.entity.asDatabaseDrink
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerCommunicator {

    fun loadFiltersList(): MutableList<String> {
        var listOfFilters = mutableListOf<String>()
        CocktailApiObj.retrofitService.getFilters("list").enqueue(object :
            Callback<Filters> {
            override fun onResponse(call: Call<Filters>, response: Response<Filters>) {
                response.body()?.apply {
                    listOfFilters = mutableListOf<String>()
                    this.drinks.forEach { drinkX -> listOfFilters.add(drinkX.strCategory) }
                }
            }

            override fun onFailure(call: Call<Filters>, t: Throwable) {
                Log.e(TAG, "getFilters failed: ${t.message}\"")
            }
        })
        return listOfFilters
    }

    fun loadCocktailsListToData(filters: List<String>?) {

        filters?.forEach { filter ->
            CocktailApiObj.retrofitService.getDrinksByFilter(filter)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {drinkList->   appDatabase.drinkDao.insertDrinks(drinkList.drinks.asDatabaseDrink(filter) ) }
        }
    }
}