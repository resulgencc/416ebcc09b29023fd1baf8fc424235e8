package com.teknasyon.satellitetracker.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.teknasyon.satellitetracker.util.filter.FilterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Satellite(
    @SerializedName("id") val id: Int = -1,
    @SerializedName("active") val isActive: Boolean = false,
    @SerializedName("name") val name: String = ""
) : Parcelable, FilterItem {
    override fun toString(): String = name

    override fun getFilterKey(): String = name
}