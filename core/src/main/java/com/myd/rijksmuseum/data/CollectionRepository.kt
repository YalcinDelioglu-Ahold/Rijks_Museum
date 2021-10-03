package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Collection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRepository @Inject internal constructor(
    private val collectionDataSource: CollectionDataSource,
    private val networkService: NetworkService
) {
    suspend fun getCollections(pageNumber: Int): Flow<List<Collection>> {
        val collections = collectionDataSource.getCollections(pageNumber)
        with(networkService.fetchCollections(pageNumber)) {
            collectionDataSource.updateCollections(this)
        }
        return collections
    }
}
