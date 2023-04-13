package com.teknasyon.satellitetracker.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SatelliteDetailsEntity::class], version = 1)
abstract class SatelliteDetailsDatabase : RoomDatabase() {
    abstract fun satelliteDetailsDao(): SatelliteDetailsDao

    companion object {
        const val SATELLITE_DETAILS_DATABASE_NAME = "satellite_details_db"
    }
}