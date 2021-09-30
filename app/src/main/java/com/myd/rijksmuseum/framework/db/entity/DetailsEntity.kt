package com.myd.rijksmuseum.framework.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class DetailsEntity(
    @PrimaryKey val id: String,
    val objectNumber: String,
    val title: String,
    val longTitle: String,
    val principalMaker: String,
    val objectTypes: List<String>,
    @Embedded val webImage: WebImage
)
