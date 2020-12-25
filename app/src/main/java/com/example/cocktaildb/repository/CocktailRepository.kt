package com.example.cocktaildb.repository

import com.example.cocktaildb.data.entity.Drink
import com.example.cocktaildb.data.entity.Filter

interface CocktailRepository {
    suspend fun loadFiltersList() : List<Filter>
    suspend fun getDrinksByFilter(filter: String): List<Drink>
}