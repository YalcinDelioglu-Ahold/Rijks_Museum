package com.myd.rijksmuseum.framework.datasource

import com.myd.rijksmuseum.data.DetailsDataSource
import com.myd.rijksmuseum.domain.Details
import com.myd.rijksmuseum.framework.db.dao.DetailsDao
import com.myd.rijksmuseum.framework.db.entity.DetailsEntity
import com.myd.rijksmuseum.framework.db.entity.WebImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDetailsDataSource @Inject internal constructor(private val detailsDao: DetailsDao) :
    DetailsDataSource {
    override suspend fun getDetails(objectNumber: String): Flow<Details> =
        detailsDao.getDetails(objectNumber).map {
            Details(
                it.id,
                it.objectNumber,
                it.title,
                it.longTitle,
                it.principalMaker,
                it.objectTypes,
                it.webImage.url
            )
        }

    override suspend fun updateDetails(details: Details) = detailsDao.updateDetails(
        DetailsEntity(
            details.id,
            details.objectNumber,
            details.title,
            details.longTitle,
            details.principalMaker,
            details.objectTypes,
            WebImage(details.imageUrl)
        )
    )
}
