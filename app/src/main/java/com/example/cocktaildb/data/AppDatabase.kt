package com.example.cocktaildb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cocktaildb.data.dao.DrinkDao
import com.example.cocktaildb.data.entity.DatabaseDrink

@Database(entities = [DatabaseDrink::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val drinkDao: DrinkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "drinkDatabase"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}