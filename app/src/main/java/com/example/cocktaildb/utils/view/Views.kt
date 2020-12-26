package com.example.cocktaildb.utils.view

import android.view.View
import com.example.cocktaildb.utils.throttleFirst
import kotlinx.coroutines.flow.Flow
import ru.ldralighieri.corbind.view.clicks

fun View.clicksWithDelay(timeoutMillis: Long = 300L): Flow<Unit> {
    return clicks().throttleFirst(timeoutMillis)
}