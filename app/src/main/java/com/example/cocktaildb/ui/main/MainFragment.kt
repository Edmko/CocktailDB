package com.example.cocktaildb.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildb.MainActivity
import com.example.cocktaildb.R
import com.example.cocktaildb.ViewModelFactory
import com.example.cocktaildb.repository.CocktailsRepository
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class MainFragment : Fragment() {
    val TAG = MainFragment::class.java.simpleName
    lateinit var controller: RecyclerController
    private val viewModel by viewModels<MainViewModel> { ViewModelFactory(CocktailsRepository(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "MainFragment started")
        loadSharedPrefs()
        viewModel.loadData(loadSharedPrefs())
        initRecyclerView()
        listenViewModel()

        filterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_filterFragment)
        }

    }

    private fun initRecyclerView() {
            controller = RecyclerController()
        recycler.apply {
            layoutManager = LinearLayoutManager(activity as Context)
            adapter = controller.adapter

        }

    }

    private fun loadSharedPrefs(): MutableSet<String> {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = mutableSetOf<String>()
        Log.d(TAG, sharedPref?.getStringSet(getString(R.string.saved_filters_key), defaultValue).toString())
        return  sharedPref?.getStringSet(getString(R.string.saved_filters_key), defaultValue)!!
    }

    private fun listenViewModel() {
        viewModel.drinksList.observe(viewLifecycleOwner, {
            controller.submitList(it)

        })
    }
}