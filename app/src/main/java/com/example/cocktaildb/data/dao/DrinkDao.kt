package com.example.cocktaildb.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktaildb.data.entity.DatabaseDrink
import com.example.cocktaildb.data.entity.Drink
import io.reactivex.Completable
import java.util.concurrent.CompletableFuture


@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDrinks(drinks : List<DatabaseDrink>)

    @Query("SELECT * FROM drink")
    fun getDrinks(): DataSource.Factory<Int, DatabaseDrink>

    @Query("SELECT * FROM drink WHERE drink_type IN (:filter)")
    fun getDrinksByType(filter: List<String>): DataSource.Factory<Int, DatabaseDrink>
}