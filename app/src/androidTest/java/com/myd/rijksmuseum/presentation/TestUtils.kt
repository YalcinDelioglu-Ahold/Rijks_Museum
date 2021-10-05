package com.myd.rijksmuseum.presentation

import com.myd.rijksmuseum.domain.Collection
import com.myd.rijksmuseum.domain.Details

fun createCollection(
    id: String,
    objectNumber: String = "",
    title: String = "",
    principalMaker: String = "",
    imageUrl: String = ""
) = Collection(
    id,
    objectNumber,
    title,
    principalMaker,
    imageUrl
)

fun createDetails(
    id: String,
    objectNumber: String = "",
    title: String = "",
    longTitle: String = "",
    principalMaker: String = "",
    objectTypes: List<String> = emptyList(),
    imageUrl: String = ""

) = Details(
    id,
    objectNumber,
    title,
    longTitle,
    principalMaker,
    objectTypes,
    imageUrl
)