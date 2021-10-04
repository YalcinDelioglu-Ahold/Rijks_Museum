package com.myd.rijksmuseum.framework.datasource

import com.myd.rijksmuseum.data.CollectionDataSource
import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.framework.db.dao.CollectionDao
import com.myd.rijksmuseum.framework.db.entity.toCollection
import com.myd.rijksmuseum.framework.db.entity.toCollectionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCollectionDataSource @Inject internal constructor(
    private val collectionDao: CollectionDao
) : CollectionDataSource {
    override suspend fun getCollections(pageNumber: Int): Flow<List<Collection>> =
        collectionDao.getCollections(pageNumber).map { entityList ->
            entityList.map {
                it.toCollection()
            }
        }

    override suspend fun updateCollections(collections: List<Collection>) =
        collectionDao.updateCollections(
            collections.map {
                it.toCollectionEntity()
            }
        )
}