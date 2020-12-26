package com.example.cocktaildb.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.data.entity.Drink
import com.example.cocktaildb.data.entity.Filter
import com.example.cocktaildb.repository.CocktailRepositoryImpl
import com.example.cocktaildb.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: CocktailRepositoryImpl
) : BaseViewModel() {

    var filterList = mutableListOf<Filter>()
    private var _drinksList = MutableLiveData<List<Drink>>()
    var offset = 0
    var drinksList = _drinksList
    var drinkListTemp = arrayListOf<Drink>()


    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            if (filterList.isEmpty()) {
                filterList.addAll(repository.loadFiltersList())
            }
            loadCocktails()
        }
    }

    fun loadCocktails() {
        viewModelScope.launch {
            if (offset>=filterList.size){
                return@launch            }
            drinkListTemp.addAll(repository.getDrinksByFilter(filterList[offset].title))
            offset++
            _drinksList.value = drinkListTemp

        }
    }
}

