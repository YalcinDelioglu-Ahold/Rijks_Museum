package com.myd.rijksmuseum.framework.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey val id: String,
    val objectNumber: String,
    val title: String,
    val principalOrFirstMaker: String,
    @Embedded val webImage: WebImage
)
