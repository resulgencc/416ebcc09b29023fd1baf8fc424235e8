package com.teknasyon.satellitetracker.data.source.local.room

import com.teknasyon.satellitetracker.data.model.SatelliteDetails
import javax.inject.Inject

class EntityMapper @Inject constructor() {

    fun mapFromEntity(entity: SatelliteDetailsEntity): SatelliteDetails =
        SatelliteDetails(
            id = entity.id,
            costPerLaunch = entity.costPerLaunch,
            firstFlightDate = entity.firstFlightDate,
            height = entity.height,
            mass = entity.mass
        )

    fun mapToEntity(satelliteDetails: SatelliteDetails): SatelliteDetailsEntity =
        SatelliteDetailsEntity(
            id = satelliteDetails.id,
            costPerLaunch = satelliteDetails.costPerLaunch,
            firstFlightDate = satelliteDetails.firstFlightDate,
            height = satelliteDetails.height,
            mass = satelliteDetails.mass
        )
}