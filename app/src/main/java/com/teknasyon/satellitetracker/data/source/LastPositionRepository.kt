package com.teknasyon.satellitetracker.data.source

import com.teknasyon.satellitetracker.data.model.Position
import com.teknasyon.satellitetracker.data.source.local.assets.SatellitePositionsAssetReader
import com.teknasyon.satellitetracker.util.LAST_POSITION_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LastPositionRepository @Inject constructor(
    private val satellitePositionsAssetReader: SatellitePositionsAssetReader
) {

    suspend fun getLastPosition(
        satelliteId: Int,
        delays: Long = LAST_POSITION_DELAY
    ): Flow<DataState<Position>> = flow {
        emit(DataState.Loading)
        try {
            val positionResponse = satellitePositionsAssetReader.convertTo()
            val satellitePosition = positionResponse.list.find { it.id == satelliteId }
            if (satellitePosition != null) {
                for (lastPosition in satellitePosition.positions) {
                    emit(DataState.Success(data = lastPosition))
                    delay(delays)
                }
            }
        } catch (ex: Exception) {
            emit(DataState.Error(exception = ex))
        }
    }
}