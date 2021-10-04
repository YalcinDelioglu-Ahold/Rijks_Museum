package com.myd.rijksmuseum.framework.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myd.rijksmuseum.domain.Details

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

fun DetailsEntity.toDetails() = Details(
    this.id,
    this.objectNumber,
    this.title,
    this.longTitle,
    this.principalMaker,
    this.objectTypes,
    this.webImage.url
)

fun Details.toDetailEntity() = DetailsEntity(
    this.id,
    this.objectNumber,
    this.title,
    this.longTitle,
    this.principalMaker,
    this.objectTypes,
    WebImage(this.imageUrl)
)
