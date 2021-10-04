package com.myd.rijksmuseum.framework.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myd.rijksmuseum.domain.Collection

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey val id: String,
    val objectNumber: String,
    val title: String,
    val principalOrFirstMaker: String,
    @Embedded val webImage: WebImage
)

fun CollectionEntity.toCollection() = Collection(
    this.id,
    this.objectNumber,
    this.title,
    this.principalOrFirstMaker,
    this.webImage.url
)

fun Collection.toCollectionEntity() = CollectionEntity(
    this.id,
    this.objectNumber,
    this.title,
    this.principalMaker,
    WebImage(this.imageUrl)
)
