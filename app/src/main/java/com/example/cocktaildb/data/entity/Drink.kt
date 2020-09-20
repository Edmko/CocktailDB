package com.example.cocktaildb.data.entity

data class Drink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)
fun List<Drink>.asDatabaseDrink(drinkType : String) : List<DatabaseDrink>{
    return map {
        DatabaseDrink(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkThumb = it.strDrinkThumb,
            drinkType = drinkType
        )
    }
}