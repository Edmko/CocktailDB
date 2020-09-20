package com.example.cocktaildb

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.cocktaildb.R2.string.filters
import com.example.cocktaildb.data.CocktailApiObj
import com.example.cocktaildb.data.entity.Filters
import com.example.cocktaildb.repository.CocktailsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFiltersList()
        navController = this.findNavController(R.id.myNavHostFragment)

    }

    private fun loadFiltersList() {
        val listOfFilters = mutableListOf<String>()
        CocktailApiObj.retrofitService.getFilters("list").enqueue(object :
            Callback<Filters> {
            override fun onResponse(call: Call<Filters>, response: Response<Filters>) {
                response.body()?.apply {
                    this.drinks.forEach { drinkX -> listOfFilters.add(drinkX.strCategory)
                        saveSharedPref(TypeConverter.listToString(listOfFilters))}
                }
            }

            override fun onFailure(call: Call<Filters>, t: Throwable) {
                Log.e(ContentValues.TAG, "getFilters failed: ${t.message}\"")
            }
        })

    }

    private fun saveSharedPref(stringOfFilters: String) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit {
            putString(
                getString(R.string.saved_filters_key),
                stringOfFilters
            )
            commit()
        }
    }

    override fun onDestroy() {
        this.getPreferences(Context.MODE_PRIVATE).edit().clear().apply()
        super.onDestroy()
    }
}
