package com.example.cocktaildb.ui.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cocktaildb.data.entity.Filter
import com.example.cocktaildb.repository.CocktailRepositoryImpl
import com.example.cocktaildb.ui.base.BaseViewModel
import com.example.cocktaildb.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class FilterViewModel @Inject constructor(private val repository: CocktailRepositoryImpl) : BaseViewModel() {
    var filtersList = listOf<Filter>()

    fun getFilters() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data= null))
            try {
                emit(Resource.success(data = repository.loadFiltersList().map { Filter(it.title) }))
            }
            catch (exception : Exception){
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
}