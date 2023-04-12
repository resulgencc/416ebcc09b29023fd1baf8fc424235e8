package com.teknasyon.satellitetracker.model

import com.google.gson.annotations.SerializedName
import com.teknasyon.satellitetracker.util.extensions.convertTo

data class SatelliteDetails(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("cost_per_launch") val costPerLaunch: Int = 0,
    @SerializedName("first_flight") private val _firstFlightDate: String = "",
    @SerializedName("height") val height: Int = 0,
    @SerializedName("mass") val mass: Int = 0
) {
    val firstFlightDate : String = _firstFlightDate.convertTo()
}