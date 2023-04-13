package com.teknasyon.satellitetracker.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Satellite(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("active") val isActive: Boolean = false,
    @SerializedName("name") val name: String = ""
): Parcelable {
    override fun toString(): String = name
}