package com.myd.rijksmuseum.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.myd.rijksmuseum.usecases.GetDetailsUseCase
import javax.inject.Inject

class DetailsViewModel @Inject internal constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel()