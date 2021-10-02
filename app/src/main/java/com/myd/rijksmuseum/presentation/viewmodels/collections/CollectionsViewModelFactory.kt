package com.myd.rijksmuseum.presentation.viewmodels.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myd.rijksmuseum.usecases.GetCollectionsUseCase
import javax.inject.Inject

class CollectionsViewModelFactory @Inject internal constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CollectionsViewModel(getCollectionsUseCase) as T
}