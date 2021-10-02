package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Details
import kotlinx.coroutines.flow.Flow

interface DetailsDataSource {
    suspend fun getDetails(objectNumber: String): Flow<Details>
    suspend fun updateDetails(details: Details)
}
