package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.domain.Details
import kotlinx.coroutines.flow.Flow

interface NetworkService {
    suspend fun fetchCollections(pageNumber: Int): Flow<List<Collection>>
    suspend fun getDetails(objectNumber: String): Flow<Details>
}