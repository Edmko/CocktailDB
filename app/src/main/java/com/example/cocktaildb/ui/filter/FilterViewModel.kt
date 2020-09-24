package com.example.cocktaildb.ui.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cocktaildb.repository.CocktailsRepository
import com.example.cocktaildb.utils.Resource
import kotlinx.coroutines.Dispatchers


class FilterViewModel(private val repository: CocktailsRepository) : ViewModel() {
    var filtersList = mutableSetOf<String>()

    fun getFilters() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data= null))
            try {
                emit(Resource.success(data = repository.loadFilterList()))
            }
            catch (exception : Exception){
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
}