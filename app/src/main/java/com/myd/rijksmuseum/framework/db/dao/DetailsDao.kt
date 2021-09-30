package com.myd.rijksmuseum.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.myd.rijksmuseum.framework.db.entity.DetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailsDao {
    @Insert(onConflict = REPLACE)
    suspend fun updateDetails(details: DetailsEntity)

    @Query("SELECT * FROM details WHERE objectNumber = :objectNumber")
    suspend fun getDetails(objectNumber: String): Flow<DetailsEntity>
}