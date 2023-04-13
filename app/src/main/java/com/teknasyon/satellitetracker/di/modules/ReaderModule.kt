package com.teknasyon.satellitetracker.di.modules

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.teknasyon.satellitetracker.data.source.local.assets.SatelliteDetailsAssetReader
import com.teknasyon.satellitetracker.data.source.local.assets.SatellitePositionsAssetReader
import com.teknasyon.satellitetracker.data.source.local.assets.SatellitesAssetReader
import com.teknasyon.satellitetracker.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReaderModule {

    @Provides
    @Singleton
    fun provideConverter(): Gson = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    @Provides
    @Singleton
    fun provideSatelliteAssetReader(assetManager: AssetManager, gson: Gson) =
        SatellitesAssetReader(assetManager, Constants.SATELLITES_FILE_NAME, gson)

    @Provides
    @Singleton
    fun provideSatelliteDetailsAssetReader(assetManager: AssetManager, gson: Gson) =
        SatelliteDetailsAssetReader(assetManager, Constants.SATELLITE_DETAILS_FILE_NAME, gson)

    @Provides
    @Singleton
    fun provideSatellitePositionsAssetReader(assetManager: AssetManager, gson: Gson) =
        SatellitePositionsAssetReader(assetManager, Constants.SATELLITES_POSITIONS_FILE_NAME, gson)
}