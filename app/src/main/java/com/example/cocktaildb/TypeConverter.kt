package com.example.cocktaildb

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.room.TypeConverter

object TypeConverter {
    @TypeConverter
    @JvmStatic
    fun stringToList(value: String): MutableList<String>? {
        return Gson().fromJson(value, object : TypeToken<MutableList<String>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(value: MutableList<String>): String {
        return Gson().toJson(value)
    }
}