package com.teknasyon.satellitetracker.ui.main

import androidx.lifecycle.*
import com.teknasyon.satellitetracker.data.model.Satellite
import com.teknasyon.satellitetracker.data.source.DataState
import com.teknasyon.satellitetracker.data.source.SatelliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SEARCH_QUERY = "search_query"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _satellites: MutableLiveData<DataState<List<Satellite>>> = MutableLiveData()
    val satellite: LiveData<DataState<List<Satellite>>> = _satellites

    val searchQuery = stateHandle.getLiveData<String?>(SEARCH_QUERY)

    fun readSatellites() {
        viewModelScope.launch {
            satelliteRepository.getSatellites()
                .onEach {
                    _satellites.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun writeToSearchQuery(filterText: String?) {
        stateHandle[SEARCH_QUERY] = filterText
    }
}