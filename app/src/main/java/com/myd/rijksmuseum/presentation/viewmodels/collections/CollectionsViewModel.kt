package com.myd.rijksmuseum.presentation.viewmodels.collections

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.launchDataFetch
import com.myd.rijksmuseum.usecases.GetCollectionsUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CollectionsViewModel @Inject internal constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase
) :
    ViewModel() {
    val pageNumberLiveData = MutableLiveData(1)

    val collectionsLiveData = MediatorLiveData<List<Collection>>().apply {
        addSource(pageNumberLiveData) { pageNumber ->
            launchDataFetch(viewModelScope) {
                getCollectionsUseCase(pageNumber).collect { resultList ->
                    postValue(resultList)
                }
            }
        }
    }
}