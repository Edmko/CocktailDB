package com.example.cocktaildb.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.cocktaildb.R
import com.example.cocktaildb.ui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment<MainViewModel>() {
    lateinit var controller: RecyclerController


    override val viewModel: MainViewModel by lazy {  provideViewModel(MainViewModel::class.java)}
    override val layout: Int = R.layout.main_fragment

    override fun onSetupLayout(view: View) {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        listenViewModel()
        filterBtn.setOnClickListener {
            findNavController().navigate( R.id.action_mainFragment_to_filterFragment)
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
    }
}