package com.example.cocktaildb.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktaildb.ui.base.events.BaseEvent
import com.example.cocktaildb.ui.base.utils.toLiveEvent

abstract class BaseViewModel: ViewModel() {

    protected val eventLiveData = MutableLiveData<BaseEvent>()
    val eventLiveEvent = eventLiveData.toLiveEvent()

}