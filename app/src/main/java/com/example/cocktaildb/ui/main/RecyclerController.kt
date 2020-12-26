package com.example.cocktaildb.ui.main

import com.airbnb.epoxy.EpoxyController
import com.example.cocktaildb.data.entity.Drink
import com.example.cocktaildb.data.entity.Filter
import com.example.cocktaildb.ui.main.models.cocktail
import com.example.cocktaildb.ui.main.models.type

class RecyclerController : EpoxyController() {

    private val drinks = mutableListOf<Drink>()
    private val filters = mutableListOf<Filter>()

    override fun buildModels() {
        filters.forEach { filter ->
            type {
                id(filter.title)
               type(filter.title)
            }
            drinks.filter { it.type == filter.title }.forEach{ item ->
                cocktail {
                    id(item.id)
                    title(item.title)
                    imageUrl(item.image?:" ")
                }
            }
        }
    }

    fun submitList(filterList: List<Filter>, drinkList: List<Drink>) {
        drinks.clear()
        filters.clear()
        drinks.addAll(drinkList)
        filters.addAll(filterList)
        requestModelBuild()
    }
}
