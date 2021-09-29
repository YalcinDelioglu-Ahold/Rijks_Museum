package com.myd.rijksmuseum.domain

data class Details(
    val id: String,
    val objectNumber: String,
    val artist: String,
    val title: String,
    val longTitle: String,
    val imageUrl: String,
    val objectType: String
)
