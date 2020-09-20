package com.example.cocktaildb.ui.main

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.cocktaildb.data.entity.DatabaseDrink
import com.example.cocktaildb.ui.main.models.CocktailModel_
import com.example.cocktaildb.ui.main.models.TypeModel_

class RecyclerController(private val filters: List<String>) :
    PagedListEpoxyController<DatabaseDrink>() {
    var filterNow = "now"
    var filterItem = "no"

    override fun buildItemModel(currentPosition: Int, item: DatabaseDrink?): EpoxyModel<*> {
        return when {
            item == null -> {
                CocktailModel_()
                    .id(-currentPosition)
                    .title("loading $currentPosition")
                    .imageUrl(" ")
            }
             filterItem!=filterNow  -> {
                 filterItem = item.drinkType
                 filterNow = filterItem

                 TypeModel_()
                     .id(filterNow)
                     .type(filterNow)
                     .title(item.strDrink)
                     .imageUrl(item.strDrinkThumb)





            }
            else -> {
                filterItem = item.drinkType
                CocktailModel_()
                    .id("drink${currentPosition}")
                    .title(item.strDrink)
                    .imageUrl(item.strDrinkThumb)
            }
        }
    }
}