package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Details
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsRepository @Inject internal constructor(
    private val detailsDataSource: DetailsDataSource,
    private val networkService: NetworkService
) {
    suspend fun getDetails(objectNumber: String): Flow<Details> {
        val details = detailsDataSource.getDetails(objectNumber)
        networkService.getDetails(objectNumber).apply {
            detailsDataSource.updateDetails(this)
        }
        return details
    }
}