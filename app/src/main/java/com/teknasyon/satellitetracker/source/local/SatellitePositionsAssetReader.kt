package com.teknasyon.satellitetracker.source.local

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teknasyon.satellitetracker.model.SatellitePositionsResponse
import java.lang.reflect.Type

class SatellitePositionsAssetReader(
    assetManager: AssetManager,
    fileName: String,
    gson: Gson
) : BaseAssetReader<SatellitePositionsResponse>(
    assetManager, fileName, gson
) {

    override val convertType: Type
        get() = object : TypeToken<SatellitePositionsResponse>() {}.type
}