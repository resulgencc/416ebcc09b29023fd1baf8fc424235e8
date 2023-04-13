package com.teknasyon.satellitetracker.data.model

import com.google.gson.annotations.SerializedName

data class SatellitePosition(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("positions") val positions: List<Position> = emptyList()
)

data class Position(
    @SerializedName("posX") val x: Float = 0F,
    @SerializedName("posY") val y: Float = 0F
) {
    override fun toString(): String = "($x,$y)"
}

data class SatellitePositionsResponse(
    @SerializedName("list") val list: List<SatellitePosition> = emptyList()
)