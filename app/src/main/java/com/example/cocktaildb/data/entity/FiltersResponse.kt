package com.example.cocktaildb.data.entity

data class FiltersResponse(
    val drinks: List<DrinkX>
){
    data class DrinkX(
        val strCategory: String
    )
}