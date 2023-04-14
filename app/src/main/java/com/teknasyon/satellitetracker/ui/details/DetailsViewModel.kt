package com.teknasyon.satellitetracker.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknasyon.satellitetracker.data.model.Position
import com.teknasyon.satellitetracker.data.model.SatelliteDetails
import com.teknasyon.satellitetracker.data.source.DataState
import com.teknasyon.satellitetracker.data.source.LastPositionRepository
import com.teknasyon.satellitetracker.data.source.SatelliteDetailsRepository
import com.teknasyon.satellitetracker.util.LAST_POSITION_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val satelliteDetailsRepository: SatelliteDetailsRepository,
    private val satellitePositionRepository: LastPositionRepository
) : ViewModel() {

    private val _satelliteDetails: MutableLiveData<DataState<SatelliteDetails>> = MutableLiveData()
    val satelliteDetails: LiveData<DataState<SatelliteDetails>> = _satelliteDetails

    private val _lastPosition: MutableLiveData<DataState<Position>> = MutableLiveData()
    val lastPosition: LiveData<DataState<Position>> = _lastPosition

    fun getDetails(satelliteId: Int) {
        viewModelScope.launch {
            satelliteDetailsRepository.getSatellites(satelliteId = satelliteId)
                .onEach {
                    _satelliteDetails.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun getLastPosition(
        satelliteId: Int,
        delays: Long = LAST_POSITION_DELAY
    ) {
        viewModelScope.launch {
            satellitePositionRepository.getLastPosition(
                satelliteId = satelliteId,
                delays = delays
            )
                .onEach {
                    _lastPosition.value = it
                }.launchIn(viewModelScope)
        }
    }
}