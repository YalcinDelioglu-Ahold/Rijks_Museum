package com.myd.rijksmuseum.presentation.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myd.rijksmuseum.domain.Details
import com.myd.rijksmuseum.launchDataFetch
import com.myd.rijksmuseum.usecases.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject internal constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    val objectNumberLiveData = MutableLiveData<String>()

    val detailsLiveData = MediatorLiveData<Details>().apply {
        addSource(objectNumberLiveData) { objectNumber ->
            launchDataFetch(viewModelScope) {
                getDetailsUseCase(objectNumber).collect { details ->
                    postValue(details)
                }
            }
        }
    }
}