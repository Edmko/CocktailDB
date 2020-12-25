package com.example.cocktaildb.di

import com.example.cocktaildb.ui.filter.FilterFragment
import com.example.cocktaildb.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {

    @ContributesAndroidInjector
    @PerFragment
    abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun filtersFragment(): FilterFragment
}