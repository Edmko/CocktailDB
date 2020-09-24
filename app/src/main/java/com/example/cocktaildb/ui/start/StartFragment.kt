package com.example.cocktaildb.ui.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cocktaildb.R
import com.example.cocktaildb.repository.CocktailsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch { saveFiltersToSharedPref(getFilters())
        delay(2000)
            findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }


    }

    private fun saveFiltersToSharedPref(filters: Set<String>) {
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPreferences.edit()) {
            putStringSet(
                getString(R.string.saved_filters_key),
                filters
            )
            commit()
        }
    }
    private suspend fun getFilters() =
        lifecycleScope.async {
            val filtersList = mutableSetOf<String>()
            CocktailsRepository().loadFilterList().drinks.forEach { drinkX -> filtersList.add(drinkX.strCategory) }
            return@async filtersList
        }.await()

}