package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Collection
import kotlinx.coroutines.flow.Flow

interface CollectionDataSource {
    suspend fun getCollections(pageNumber: Int): Flow<List<Collection>>
    suspend fun updateCollections(collections: List<Collection>)
}
