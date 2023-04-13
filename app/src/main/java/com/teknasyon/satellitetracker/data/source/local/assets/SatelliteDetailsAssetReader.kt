package com.teknasyon.satellitetracker.data.source.local.assets

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teknasyon.satellitetracker.data.model.SatelliteDetails
import java.lang.reflect.Type

class SatelliteDetailsAssetReader(
    assetManager: AssetManager,
    fileName: String,
    gson: Gson
): BaseAssetReader<List<SatelliteDetails>>(assetManager, fileName, gson) {

    override val convertType: Type
        get() = object : TypeToken<List<SatelliteDetails>>() {}.type
}