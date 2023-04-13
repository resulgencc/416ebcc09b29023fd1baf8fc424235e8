package com.teknasyon.satellitetracker.data.model

import com.google.gson.annotations.SerializedName
import com.teknasyon.satellitetracker.util.extensions.convertTo
import com.teknasyon.satellitetracker.util.extensions.showWithSeparatorDots

data class SatelliteDetails(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("cost_per_launch") val costPerLaunch: Int = 0,
    @SerializedName("first_flight") val firstFlightDate: String = "",
    @SerializedName("height") val height: Int = 0,
    @SerializedName("mass") val mass: Int = 0
) {
    fun formattedFirstFlightDate() : String = firstFlightDate.convertTo()

    fun formattedHeightMass(): String = "$height/$mass"

    fun formattedCostPerLaunch(): String = costPerLaunch.showWithSeparatorDots()
}