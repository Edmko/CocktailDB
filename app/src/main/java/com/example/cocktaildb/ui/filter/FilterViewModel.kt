package com.example.cocktaildb.ui.filter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktaildb.TypeConverter
import com.example.cocktaildb.data.CocktailApiObj
import com.example.cocktaildb.data.entity.Filters
import com.example.cocktaildb.repository.CocktailsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterViewModel(private val repository: CocktailsRepository) : ViewModel() {

    private val _filtersLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val filtersLiveData: LiveData<List<String>> get() = _filtersLiveData
    var filtersList = mutableListOf<String>()

    fun getListOfFilter(arrayListOfFilters: ArrayList<String>): String {
        val listOfFilters = mutableListOf<String>()
        listOfFilters.addAll(arrayListOfFilters)

        val filtersString = TypeConverter.listToString(listOfFilters)

        return if (!filtersString.isBlank()) {
            filtersString
        } else ""
    }


    fun loadFiltersList() {
        val listOfFilters = mutableListOf<String>()
        CocktailApiObj.retrofitService.getFilters("list").enqueue(object :
            Callback<Filters> {
            override fun onResponse(call: Call<Filters>, response: Response<Filters>) {
                response.body()?.apply {
                    this.drinks.forEach { drinkX -> listOfFilters.add(drinkX.strCategory)
                    }
                    _filtersLiveData.postValue(listOfFilters.sorted())
                }
            }

            override fun onFailure(call: Call<Filters>, t: Throwable) {
                Log.e(ContentValues.TAG, "getFilters failed: ${t.message}\"")
            }
        })

    }
}