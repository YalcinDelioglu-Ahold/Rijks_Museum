package com.myd.rijksmuseum.presentation.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.launchDataFetch
import com.myd.rijksmuseum.presentation.di.FragmentScope
import com.myd.rijksmuseum.usecases.GetCollectionsUseCase
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@FragmentScope
class CollectionsViewModel @Inject internal constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase
) : ViewModel() {
    private val pageNumberLiveData = MutableLiveData(1)

    val collectionsLiveData = MediatorLiveData<List<Collection>>().apply {
        addSource(pageNumberLiveData) { pageNumber ->
            launchDataFetch(viewModelScope) {
                getCollectionsUseCase(pageNumber).collect { resultList ->
                    postValue(resultList)
                }
            }
        }
    }

    fun loadMore() {
        val currentPageNumber = pageNumberLiveData.value
        pageNumberLiveData.postValue(currentPageNumber?.plus(1))
    }
}