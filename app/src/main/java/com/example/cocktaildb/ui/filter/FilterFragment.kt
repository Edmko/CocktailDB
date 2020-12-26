package com.example.cocktaildb.ui.filter

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildb.R
import com.example.cocktaildb.ui.base.BaseFragment
import com.example.cocktaildb.utils.view.clicksWithDelay
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FilterFragment : BaseFragment<FilterViewModel>() {


    override val layout: Int = R.layout.filter_fragment

    override val viewModel: FilterViewModel by lazy { provideViewModel(FilterViewModel::class.java) }

    private val args: FilterFragmentArgs by navArgs()

    override fun onSetupLayout(view: View) {
        initRecyclerView()
        listenButtons()
        listenViewModel()
    }

    private fun initRecyclerView() {
        filtersRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FiltersAdapter()
        }
    }

    private fun listenButtons() {
        launch {
            imgBack.clicksWithDelay().collect {
                findNavController().popBackStack()
            }
        }
        launch {
            btnApply.clicksWithDelay().collect {
                val listOfFilters =
                    (filtersRecycler.adapter as FiltersAdapter).getListOfFilters().toTypedArray()
                val action =
                    FilterFragmentDirections.actionFilterFragmentToMainFragment(listOfFilters)
                findNavController().navigate(action)
            }
        }
    }

    private fun listenViewModel() {
        viewModel.getFilters().observe(viewLifecycleOwner, {
            (filtersRecycler.adapter as FiltersAdapter).setData(
                it?.data ?: emptyList(),
                args.filterList.toList()
            )
        })
    }

}