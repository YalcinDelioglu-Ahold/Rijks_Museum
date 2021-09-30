package com.myd.rijksmuseum.domain

data class Collection(
    val id: String,
    val objectNumber: String,
    val title: String,
    val principalMaker: String,
    val imageUrl: String
)
