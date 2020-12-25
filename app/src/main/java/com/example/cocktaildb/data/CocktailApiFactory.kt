package com.example.cocktaildb.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CocktailApiFactory {

    fun newInstance(
        endpoint: String,
        ganderInterceptor: Interceptor
    ): CocktailApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(endpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient(ganderInterceptor))
            .build()
        return retrofit.create(CocktailApi::class.java)
    }

    private fun okHttpClient(interceptor: Interceptor): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()

        return OkHttpClient.Builder().apply {

            addInterceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder().method(request.method, request.body)
                val mutatedRequest = builder.build()
                val response = chain.proceed(mutatedRequest)
                response
            }
            addInterceptor(loggingInterceptor)
            addInterceptor(interceptor)
        }.build()
    }
}