package com.example.cocktaildb.ui.base.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cocktaildb.ui.base.LiveEvent

@Suppress("detekt.UnsafeCast")
fun <T> MutableLiveData<T>.toLiveData() = this as LiveData<T>

fun <T> LiveData<T>.toLiveEvent(): LiveData<T> {
    val result = LiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}