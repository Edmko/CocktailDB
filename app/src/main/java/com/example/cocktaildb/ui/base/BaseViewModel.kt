package com.example.cocktaildb.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktaildb.ui.base.events.BaseEvent
import com.example.cocktaildb.ui.base.utils.toLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel(), CoroutineScope {
    private val scopeJob: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext = scopeJob + Dispatchers.Main

    protected val eventLiveData = MutableLiveData<BaseEvent>()
    val eventLiveEvent = eventLiveData.toLiveEvent()

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}