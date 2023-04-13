package com.teknasyon.satellitetracker.data.source.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_satellite_details")
data class SatelliteDetailsEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("cost_per_launch") val costPerLaunch: Int,
    @ColumnInfo("first_flight") val firstFlightDate: String,
    @ColumnInfo("height") val height: Int,
    @ColumnInfo("mass") val mass: Int
)