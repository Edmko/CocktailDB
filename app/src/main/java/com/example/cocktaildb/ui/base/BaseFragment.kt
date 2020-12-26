package com.example.cocktaildb.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildb.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VM : BaseViewModel> : Fragment(), CoroutineScope {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract val layout: Int

    private val scopeJob: Job = SupervisorJob()

    abstract val viewModel: VM

    override val coroutineContext: CoroutineContext = scopeJob + Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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