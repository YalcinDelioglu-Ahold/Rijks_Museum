package com.myd.rijksmuseum.domain

data class Details(
    val id: String,
    val objectNumber: String,
    val title: String,
    val longTitle: String,
    val principalMaker: String,
    val objectTypes: List<String>,
    val imageUrl: String
)
