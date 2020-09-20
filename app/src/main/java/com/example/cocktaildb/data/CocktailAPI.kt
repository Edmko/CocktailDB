package com.example.cocktaildb.data

import com.example.cocktaildb.data.entity.DrinksList
import com.example.cocktaildb.data.entity.Filters
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
interface CocktailAPI {
    @GET("filter.php")
    fun getDrinksByFilter(
        @Query("c") list : String): Single<DrinksList>

    @GET("list.php")
    fun getFilters(
        @Query("c") filters: String) : Call<Filters>
}

object CocktailApiObj{
    val retrofitService : CocktailAPI by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = (HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
            BASE_URL
        ).client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        retrofit.create(CocktailAPI::class.java)
    }
}