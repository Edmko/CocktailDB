package com.example.cocktaildb.ui.filter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildb.R
import com.example.cocktaildb.data.entity.Filter
import com.example.cocktaildb.ui.base.BaseFragment
import kotlinx.android.synthetic.main.filter_fragment.*

class FilterFragment : BaseFragment<FilterViewModel>(), View.OnClickListener {


    override val layout: Int = R.layout.filter_fragment

    override val viewModel: FilterViewModel by lazy { provideViewModel(FilterViewModel::class.java) }

    override fun onSetupLayout(view: View) {

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
            )!!.map { Filter(it) }
    }

    private fun listenViewModel() {
        viewModel.getFilters().observe(viewLifecycleOwner, {
            (filtersRecycler.adapter as FiltersAdapter).setData(
                it?.data?: emptyList(),
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