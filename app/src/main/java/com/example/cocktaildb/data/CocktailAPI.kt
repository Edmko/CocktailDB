package com.example.cocktaildb.data

import com.example.cocktaildb.data.entity.DrinksList
import com.example.cocktaildb.data.entity.Filters
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

interface CocktailAPI {
    @GET("filter.php")
    fun getDrinksByFilter(
        @Query("c") filter: String
    ): Single<DrinksList>

    @GET("list.php")
    suspend fun getFilters(
        @Query("c") filters: String
    ): Filters


    @GET("filter.php")
    suspend fun getDrinksByFilterSuspend(
        @Query("c") filter: String
    ): DrinksList
}

object CocktailApiObj {
    val retrofitService: CocktailAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(CocktailAPI::class.java)
    }
}