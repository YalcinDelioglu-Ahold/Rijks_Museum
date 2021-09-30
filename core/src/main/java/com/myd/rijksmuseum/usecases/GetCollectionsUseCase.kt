package com.myd.rijksmuseum.usecases

import com.myd.rijksmuseum.data.CollectionRepository
import com.myd.rijksmuseum.domain.Collection
import kotlinx.coroutines.flow.Flow

class GetCollectionsUseCase(private val collectionRepository: CollectionRepository) {
    suspend operator fun invoke(pageNumber: Int): Flow<List<Collection>> {
        return collectionRepository.getCollections(pageNumber)
    }
}
