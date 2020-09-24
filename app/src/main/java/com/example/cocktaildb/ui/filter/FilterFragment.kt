package com.example.cocktaildb.ui.filter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildb.R
import com.example.cocktaildb.ViewModelFactory

import com.example.cocktaildb.repository.CocktailsRepository
import com.example.cocktaildb.utils.Status
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.coroutines.delay

class FilterFragment : Fragment(), View.OnClickListener {


    private val viewModel by viewModels<FilterViewModel> {
        ViewModelFactory(
            CocktailsRepository(),
            this
        )
    }

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
        initButtons()
        listenViewModel()

    }


    private fun initButtons() {
        backIcon.setOnClickListener(this)
        applyBtn.setOnClickListener(this)
    }

    private fun initRecyclerView() {

        filtersRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FiltersAdapter()
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

    private fun loadSharedPrefs() {
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val defaultValue = mutableSetOf<String>()
        viewModel.filtersList=
            sharedPreferences.getStringSet(
                resources.getString(R.string.saved_filters_key),
                defaultValue
            )!!
    }

    private fun listenViewModel() {
        viewModel.getFilters().observe(viewLifecycleOwner, {
            (filtersRecycler.adapter as FiltersAdapter).setData(
                it?.data,
                viewModel.filtersList
            )
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            backIcon -> findNavController().navigate(R.id.action_filterFragment_to_mainFragment)
            applyBtn -> {
                val listOfFilters =
                    (filtersRecycler.adapter as FiltersAdapter).getListOfFilters()
                Log.d(FilterFragment::class.java.simpleName, "List: $listOfFilters")
                saveFiltersToSharedPref(listOfFilters)

                findNavController().navigate(R.id.action_filterFragment_to_mainFragment)
            }
        }
    }


}