package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.domain.Details

interface NetworkService {
    suspend fun fetchCollections(pageNumber: Int): List<Collection>
    suspend fun getDetails(objectNumber: String): Details
}