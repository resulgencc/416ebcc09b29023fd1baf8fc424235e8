package com.teknasyon.satellitetracker.source.local

import android.content.res.AssetManager
import com.google.gson.Gson
import java.lang.reflect.Type
import java.nio.charset.Charset

abstract class BaseAssetReader<T>(
    private val assetManager: AssetManager,
    private val fileName: String,
    private val gson: Gson
) {

    abstract val convertType: Type

    @Throws(Exception::class)
    private fun convertToString(): String {
        val inputStream = assetManager.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.forName("UTF-8"))
    }

    @Throws(Exception::class)
    fun convertTo(): T {
        val json = convertToString()
        return gson.fromJson(json, convertType)
    }


}