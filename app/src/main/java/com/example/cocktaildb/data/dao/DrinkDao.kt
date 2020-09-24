package com.example.cocktaildb.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktaildb.data.entity.DatabaseDrink


@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDrinks(drinks : List<DatabaseDrink>)

    @Query("SELECT * FROM drink")
    fun getDrinks(): DataSource.Factory<Int, DatabaseDrink>

    @Query("SELECT * FROM drink WHERE drink_type IN (:filter)")
    fun observeDrinksByType(filter: MutableSet<String>): DataSource.Factory<Int, DatabaseDrink>
}