package com.example.cocktaildb.repository

import com.example.cocktaildb.data.CocktailApi
import com.example.cocktaildb.data.entity.Drink
import com.example.cocktaildb.data.entity.Filter
import com.example.cocktaildb.data.entity.asDatabaseDrink
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val api: CocktailApi
) : CocktailRepository {

    override suspend fun loadFiltersList(): List<Filter> {
        return api.getFilters().drinks.map { drinkX -> Filter(drinkX.strCategory) }
    }

    override suspend fun getDrinksByFilter(filter: String): List<Drink> {
        return api.getDrinksByFilter(filter).drinks.asDatabaseDrink(filter)
    }
}