package com.teknasyon.satellitetracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknasyon.satellitetracker.model.Satellite
import com.teknasyon.satellitetracker.source.DataState
import com.teknasyon.satellitetracker.source.SatelliteDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val satelliteDataRepository: SatelliteDataRepository
): ViewModel(){

    private val _satellites : MutableLiveData<DataState<List<Satellite>>> = MutableLiveData()
    val satellite : LiveData<DataState<List<Satellite>>> = _satellites

    fun readSatellites(){
        viewModelScope.launch {
            satelliteDataRepository.getSatellites()
                .onEach {
                    _satellites.value = it
                }.launchIn(viewModelScope)
        }
    }

}