package com.example.cocktaildb.ui.filter

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildb.R
import com.example.cocktaildb.ui.base.BaseFragment
import kotlinx.android.synthetic.main.filter_fragment.*

class FilterFragment : BaseFragment<FilterViewModel>(), View.OnClickListener {


    override val layout: Int = R.layout.filter_fragment

    override val viewModel: FilterViewModel by lazy { provideViewModel(FilterViewModel::class.java) }

    private val args: FilterFragmentArgs by navArgs()

    override fun onSetupLayout(view: View) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
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

    private fun listenViewModel() {
        viewModel.getFilters().observe(viewLifecycleOwner, {
            (filtersRecycler.adapter as FiltersAdapter).setData(
                it?.data?: emptyList(),
                args.filterList.toList()
            )
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            backIcon -> findNavController().popBackStack()
            applyBtn -> {
                val listOfFilters =
                    (filtersRecycler.adapter as FiltersAdapter).getListOfFilters().toTypedArray()
                val action = FilterFragmentDirections.actionFilterFragmentToMainFragment(listOfFilters)
                findNavController().navigate(action)
            }
        }
    }


}