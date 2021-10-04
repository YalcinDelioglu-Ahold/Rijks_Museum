package com.myd.rijksmuseum.framework.datasource

import com.myd.rijksmuseum.data.DetailsDataSource
import com.myd.rijksmuseum.domain.Details
import com.myd.rijksmuseum.framework.db.dao.DetailsDao
import com.myd.rijksmuseum.framework.db.entity.toDetailEntity
import com.myd.rijksmuseum.framework.db.entity.toDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDetailsDataSource @Inject internal constructor(private val detailsDao: DetailsDao) :
    DetailsDataSource {
    override suspend fun getDetails(objectNumber: String): Flow<Details> =
        detailsDao.getDetails(objectNumber).map {
            it.toDetails()
        }

    override suspend fun updateDetails(details: Details) = detailsDao.updateDetails(
        details.toDetailEntity()
    )
}
