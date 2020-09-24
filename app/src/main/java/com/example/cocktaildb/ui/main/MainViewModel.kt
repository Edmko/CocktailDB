package com.example.cocktaildb.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.cocktaildb.data.entity.DatabaseDrink
import com.example.cocktaildb.repository.CocktailsRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CocktailsRepository) : ViewModel() {

    lateinit var drinksList: LiveData<PagedList<DatabaseDrink>>
    private val config =
        PagedList.Config.Builder().setPageSize(20).setEnablePlaceholders(false).build()

    fun loadData(filters: MutableSet<String>) {
        drinksList =
            LivePagedListBuilder(repository.observeDrinksByFilters(filters), config).build()

        viewModelScope.launch {
            repository.saveDrinksToLocalDataSource(filters)
        }
    }
}

