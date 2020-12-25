package com.example.cocktaildb.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildb.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VM: BaseViewModel>: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract val layout: Int

    abstract val viewModel : VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (view != null) view else inflater.inflate(layout, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSetupLayout(view)
    }

    abstract fun onSetupLayout(view: View)

    protected fun provideViewModel(
        viewModelClass: Class<VM>,
        activity: FragmentActivity? = null
    ): VM {
        return if (activity != null) {
            ViewModelProvider(activity, viewModelFactory).get(viewModelClass)
        } else {
            ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        }
    }
}