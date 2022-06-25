package com.ka.favcin.newarch.di

import androidx.lifecycle.ViewModel
import com.ka.favcin.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindCoinViewModel(viewModel: HomeViewModel): ViewModel
}