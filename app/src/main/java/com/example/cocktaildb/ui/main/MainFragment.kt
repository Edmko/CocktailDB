package com.example.cocktaildb.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cocktaildb.R
import com.example.cocktaildb.data.entity.Filter
import com.example.cocktaildb.ui.base.BaseFragment
import com.example.cocktaildb.ui.main.events.HideMoreButton
import com.example.cocktaildb.ui.main.events.ShowMoreButton
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : BaseFragment<MainViewModel>() {

    @Inject
    lateinit var controller: RecyclerController

    override val viewModel: MainViewModel by lazy {  provideViewModel(MainViewModel::class.java)}

    override val layout: Int = R.layout.main_fragment

    private val args : MainFragmentArgs by navArgs()

    override fun onSetupLayout(view: View) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        listenViewModel()
        viewModel.setFilters(args.filterList?.toList())
        filterBtn.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToFilterFragment(viewModel.getFilters().toTypedArray())
            findNavController().navigate(action)
        }
        btnShowMore.setOnClickListener {
            viewModel.loadCocktails()
        }

    }

    private fun initRecyclerView() {
        controller = RecyclerController()
        recycler.adapter = controller.adapter
    }

    private fun listenViewModel() {
        viewModel.drinksList.observe(viewLifecycleOwner, { drinks ->
            controller.submitList(viewModel.filterList.subList(0, viewModel.offset), drinks)

        })

        viewModel.eventLiveEvent.observe(viewLifecycleOwner, {events ->
            when(events){
                HideMoreButton -> btnShowMore.visibility = View.GONE
                ShowMoreButton -> btnShowMore.visibility = View.VISIBLE
            }
        })
    }
}