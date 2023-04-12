package com.teknasyon.satellitetracker.model

import com.google.gson.annotations.SerializedName

data class Satellite(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("active") val isActive: Boolean = false,
    @SerializedName("name") val name: String = ""
) {
    override fun toString(): String = name
}