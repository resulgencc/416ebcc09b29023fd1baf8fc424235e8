package com.teknasyon.satellitetracker.data.source

import com.teknasyon.satellitetracker.data.model.Satellite
import com.teknasyon.satellitetracker.data.source.local.assets.SatellitesAssetReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteRepository @Inject constructor(
    private val satellitesAssetReader: SatellitesAssetReader
) {
    suspend fun getSatellites(): Flow<DataState<List<Satellite>>> = flow {
        emit(DataState.Loading)
        try {
            val list = satellitesAssetReader.convertTo()
            emit(DataState.Success(data = list))
        }catch (ex: Exception){
            emit(DataState.Error(ex))
        }
    }

}