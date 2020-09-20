package com.example.cocktaildb.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.cocktaildb.data.entity.DatabaseDrink
import com.example.cocktaildb.repository.CocktailsRepository

class MainViewModel(private val repository: CocktailsRepository) : ViewModel() {
    lateinit var drinksList: LiveData<PagedList<DatabaseDrink>>
    fun loadData(filters: List<String>) {
        val config =
            PagedList.Config.Builder().setPageSize(10).setEnablePlaceholders(false).build()
        drinksList = if (filters.isEmpty()) {
            LivePagedListBuilder(repository.observeDrink(), config).build()
        } else
            LivePagedListBuilder(repository.getDrinkByType(filters.sorted()), config)
                .build()

    }

}