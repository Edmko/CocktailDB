package com.example.cocktaildb.data.entity

data class DrinksList(
    val drinks: List<Drink>
) {
    data class Drink(
        val idDrink: String,
        val strDrink: String,
        val strDrinkThumb: String
    )
}

fun List<DrinksList.Drink>.asDatabaseDrink(drinkType : String) : List<Drink>{
    return map {
        Drink(
            id = it.idDrink,
            title = it.strDrink,
            image = it.strDrinkThumb,
            type = drinkType
        )
    }
}