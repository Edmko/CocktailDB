package com.example.cocktaildb.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.airbnb.epoxy.stickyheader.StickyHeaderLinearLayoutManager
import com.example.cocktaildb.R
import com.example.cocktaildb.TypeConverter
import com.example.cocktaildb.ViewModelFactory
import com.example.cocktaildb.data.dao.DrinkDao
import com.example.cocktaildb.repository.CocktailsLocalDataSource
import com.example.cocktaildb.repository.CocktailsRepository
import com.example.cocktaildb.repository.ServerCommunicator
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    lateinit var controller: RecyclerController
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory(
            CocktailsRepository(),
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadData(loadSharedPrefs())
        initRecyclerView()
        listenViewModel()

        filterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filterFragment)
        }

    }

    private fun initRecyclerView() {

           controller = RecyclerController(loadSharedPrefs())
        recycler.apply {
            layoutManager = LinearLayoutManager(activity as Context)
            adapter = controller.adapter

        }

    }

    private fun loadSharedPrefs(): List<String> {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = resources.getString(R.string.saved_filters_default_key)
        val stringOfFilters =
            sharedPref?.getString(getString(R.string.saved_filters_key), defaultValue)!!
        var filtersList = mutableListOf<String>()
        if (stringOfFilters != defaultValue) {
            filtersList = TypeConverter.stringToList(stringOfFilters) ?: mutableListOf(defaultValue)
        }
        return filtersList
    }
    private fun listenViewModel(){
        viewModel.drinksList.observe(viewLifecycleOwner, {
            controller.submitList(it)

    })
    }
}