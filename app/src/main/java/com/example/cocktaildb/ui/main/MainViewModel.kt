package com.example.cocktaildb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.data.entity.Drink
import com.example.cocktaildb.data.entity.Filter
import com.example.cocktaildb.repository.CocktailRepositoryImpl
import com.example.cocktaildb.ui.base.BaseViewModel
import com.example.cocktaildb.ui.base.utils.toLiveData
import com.example.cocktaildb.ui.main.events.HideMoreButton
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: CocktailRepositoryImpl
) : BaseViewModel() {

    var filterList = mutableListOf<Filter>()

    private var isFiltersSelected = false

    private var _drinksList = MutableLiveData<List<Drink>>()
    var drinksList = _drinksList.toLiveData()

    var offset = 0


    fun setFilters(filters: List<Filter>?) {
        isFiltersSelected = true
        filterList.clear()
        filters?.let {filterList.addAll(it) }
        loadData()
    }

    fun getFilters(): List<Filter> {
        return if (isFiltersSelected) filterList else emptyList()
    }

    private fun loadData() {
        viewModelScope.launch {
            if (filterList.isEmpty()) {
                isFiltersSelected = false
                filterList.addAll(repository.loadFiltersList())
            }
            loadCocktails()
        }
    }

    fun loadCocktails() {
        viewModelScope.launch {

            val tempDrinks = mutableListOf<Drink>()
           tempDrinks.addAll(_drinksList.value?: emptyList())
            tempDrinks.addAll(repository.getDrinksByFilter(filterList[offset].title))
            offset++
            _drinksList.value = tempDrinks
            if (offset >= filterList.size) {
                eventLiveData.value = HideMoreButton
            }
        }
    }
}

