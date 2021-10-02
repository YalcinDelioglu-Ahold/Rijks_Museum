package com.myd.rijksmuseum.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myd.rijksmuseum.StringListConverter
import com.myd.rijksmuseum.framework.db.dao.CollectionsDao
import com.myd.rijksmuseum.framework.db.dao.DetailsDao
import com.myd.rijksmuseum.framework.db.entity.CollectionEntity
import com.myd.rijksmuseum.framework.db.entity.DetailsEntity

@Database(entities = [CollectionEntity::class, DetailsEntity::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionsDao
    abstract fun detailsDao(): DetailsDao
}
