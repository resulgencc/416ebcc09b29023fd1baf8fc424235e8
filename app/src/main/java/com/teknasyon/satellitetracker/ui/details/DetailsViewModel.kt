package com.teknasyon.satellitetracker.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknasyon.satellitetracker.data.model.SatelliteDetails
import com.teknasyon.satellitetracker.data.source.DataState
import com.teknasyon.satellitetracker.data.source.SatelliteDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val satelliteDetailsRepository: SatelliteDetailsRepository
): ViewModel(){

    private val _satelliteDetails : MutableLiveData<DataState<SatelliteDetails>> = MutableLiveData()
    val satelliteDetails: LiveData<DataState<SatelliteDetails>> = _satelliteDetails

    fun getDetails(satelliteId: Int){
        viewModelScope.launch {
            satelliteDetailsRepository.getSatellites(satelliteId = satelliteId)
                .onEach {
                    _satelliteDetails.value = it
                }.launchIn(viewModelScope)
        }
    }
}