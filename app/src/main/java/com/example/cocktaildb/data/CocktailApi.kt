package com.example.cocktaildb.data

import com.example.cocktaildb.data.entity.DrinksList
import com.example.cocktaildb.data.entity.FiltersResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("list.php?c=list")
    suspend fun getFilters(): FiltersResponse


    @GET("filter.php")
    suspend fun getDrinksByFilter(
        @Query("c") filter: String
    ): DrinksList
}

