package com.example.cocktaildb.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink")
data class DatabaseDrink (
    @PrimaryKey
    @ColumnInfo(name ="drink_id") var idDrink: String,
    @ColumnInfo(name ="drink_name") var strDrink: String,
    @ColumnInfo(name ="drink_url") var strDrinkThumb: String,
    @ColumnInfo(name ="drink_type") var drinkType : String
    )
