package com.myd.rijksmuseum.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myd.rijksmuseum.presentation.viewmodels.CollectionsViewModel
import com.myd.rijksmuseum.presentation.viewmodels.DetailsViewModel
import com.myd.rijksmuseum.presentation.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @FragmentScope
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(CollectionsViewModel::class)
    internal abstract fun bindCollectionsViewModel(viewModel: CollectionsViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}