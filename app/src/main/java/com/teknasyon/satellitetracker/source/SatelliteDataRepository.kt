package com.teknasyon.satellitetracker.source

import com.teknasyon.satellitetracker.model.Satellite
import com.teknasyon.satellitetracker.source.local.SatellitesAssetReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteDataRepository @Inject constructor(
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