package com.myd.rijksmuseum.usecases

import com.myd.rijksmuseum.data.DetailsRepository
import com.myd.rijksmuseum.domain.Details
import kotlinx.coroutines.flow.Flow

class GetDetailsUseCase(private val detailsRepository: DetailsRepository) {
    suspend operator fun invoke(objectNumber: String): Flow<Details> {
        return detailsRepository.getDetails(objectNumber)
    }
}
