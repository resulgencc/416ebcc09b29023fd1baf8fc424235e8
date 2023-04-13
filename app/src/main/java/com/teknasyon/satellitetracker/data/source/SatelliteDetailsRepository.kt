package com.teknasyon.satellitetracker.data.source

import com.teknasyon.satellitetracker.data.model.SatelliteDetails
import com.teknasyon.satellitetracker.data.source.local.assets.SatelliteDetailsAssetReader
import com.teknasyon.satellitetracker.data.source.local.room.EntityMapper
import com.teknasyon.satellitetracker.data.source.local.room.SatelliteDetailsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteDetailsRepository @Inject constructor(
    private val satelliteDetailsAssetReader: SatelliteDetailsAssetReader,
    private val satelliteDetailsDao: SatelliteDetailsDao,
    private val mapper: EntityMapper
) {

    suspend fun getSatellites(satelliteId: Int): Flow<DataState<SatelliteDetails>> = flow {
        emit(DataState.Loading)
        try {
            val entity = satelliteDetailsDao.findById(satelliteId)
            if (entity != null) {
                val satelliteDetails = mapper.mapFromEntity(entity)
                emit(DataState.Success(data = satelliteDetails))
            } else {
                val list = satelliteDetailsAssetReader.convertTo()
                for (item in list) {
                    if (item.id == satelliteId) {
                        val newEntity = mapper.mapToEntity(item)
                        satelliteDetailsDao.addToCache(newEntity)
                        emit(DataState.Success(data = item))
                    }
                }
            }
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }
}