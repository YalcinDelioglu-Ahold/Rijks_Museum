package com.myd.rijksmuseum.framework.datasource

import com.myd.rijksmuseum.data.CollectionDataSource
import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.framework.db.dao.CollectionsDao
import com.myd.rijksmuseum.framework.db.entity.CollectionEntity
import com.myd.rijksmuseum.framework.db.entity.WebImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCollectionsDataSource @Inject internal constructor(
    private val collectionsDao: CollectionsDao
) : CollectionDataSource {
    override suspend fun getCollections(pageNumber: Int): Flow<List<Collection>> =
        collectionsDao.getCollections(pageNumber).map { entityList ->
            entityList.map {
                Collection(
                    it.id,
                    it.objectNumber,
                    it.title,
                    it.principalOrFirstMaker,
                    it.webImage.url
                )
            }
        }

    override suspend fun updateCollections(collections: List<Collection>) =
        collectionsDao.updateCollections(
            collections.map {
                CollectionEntity(
                    it.id,
                    it.objectNumber,
                    it.title,
                    it.principalMaker,
                    WebImage(it.imageUrl)
                )
            }
        )
}