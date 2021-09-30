package com.myd.rijksmuseum.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.myd.rijksmuseum.framework.db.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow



@Dao
interface CollectionsDao {
    companion object {
        private const val PAGE_SIZE = 10
    }
    @Insert(onConflict = REPLACE)
    suspend fun updateCollections(collections: List<CollectionEntity>)

    @Query("SELECT * FROM collections ORDER BY principalOrFirstMaker LIMIT ($PAGE_SIZE * :pageNumber)")
    suspend fun getCollections(pageNumber: Int): Flow<List<CollectionEntity>>
}