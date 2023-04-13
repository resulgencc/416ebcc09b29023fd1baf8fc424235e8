package com.teknasyon.satellitetracker.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SatelliteDetailsDao {

    @Query("SELECT * FROM table_satellite_details WHERE id=:id")
    suspend fun findById(id: Int): SatelliteDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCache(entity: SatelliteDetailsEntity)
}