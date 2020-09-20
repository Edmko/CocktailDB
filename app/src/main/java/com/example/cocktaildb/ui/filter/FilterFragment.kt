package com.example.cocktaildb.ui.filter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildb.R
import com.example.cocktaildb.TypeConverter
import com.example.cocktaildb.ViewModelFactory
import com.example.cocktaildb.data.entity.DrinkX
import com.example.cocktaildb.repository.CocktailsRepository
import com.example.cocktaildb.ui.main.MainFragmentDirections
import com.example.cocktaildb.ui.main.MainViewModel
import kotlinx.android.synthetic.main.filter_fragment.*

class FilterFragment : Fragment() {


    private val viewModel by viewModels<FilterViewModel>{ ViewModelFactory(CocktailsRepository(),this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        loadSharedPrefs()
        viewModel.loadFiltersList()
        listenViewModel()

        backIcon.setOnClickListener {
            val listOfFilters =  viewModel.getListOfFilter((filtersRecycler.adapter as FiltersAdapter).getArrayOfFilters())
            if (!listOfFilters.isBlank()){saveSharedPref(listOfFilters)}
            findNavController().navigate(R.id.action_filterFragment_to_mainFragment)
        }

        applyBtn.setOnClickListener {
            val listOfFilters =  viewModel.getListOfFilter((filtersRecycler.adapter as FiltersAdapter).getArrayOfFilters())
            if (!listOfFilters.isBlank()){saveSharedPref(listOfFilters)}

            findNavController().navigate(R.id.action_filterFragment_to_mainFragment)
        }

    }

    private fun loadSharedPrefs() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getString(R.string.saved_filters_default_key)
        val stringOfFilters =
            sharedPref.getString(getString(R.string.saved_filters_key), defaultValue)!!

        if (stringOfFilters != defaultValue) {
           viewModel.filtersList = TypeConverter.stringToList(stringOfFilters) ?: mutableListOf(defaultValue)
        }

    }

    private fun initRecyclerView() {

        filtersRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FiltersAdapter()
        }
    }

    private fun saveSharedPref(filters: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit {
            putString(
                getString(R.string.saved_filters_key),
               filters
            )
            commit()
        }
    }

    private fun listenViewModel() {
        viewModel.filtersLiveData.observe(viewLifecycleOwner, {
            (filtersRecycler.adapter as FiltersAdapter).setData(it, viewModel.filtersList)
        })
    }


}