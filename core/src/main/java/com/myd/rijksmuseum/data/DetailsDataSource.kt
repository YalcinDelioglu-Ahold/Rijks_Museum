package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Details
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface DetailsDataSource {
    suspend fun getDetails(objectNumber: String): MutableSharedFlow<Details>
    suspend fun updateDetails(details: Details)
}