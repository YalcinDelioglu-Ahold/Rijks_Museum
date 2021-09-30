package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Collection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface CollectionDataSource {
    suspend fun getCollections(pageNumber: Int): MutableSharedFlow<List<Collection>>
    suspend fun updateCollections(collections: List<Collection>)
}