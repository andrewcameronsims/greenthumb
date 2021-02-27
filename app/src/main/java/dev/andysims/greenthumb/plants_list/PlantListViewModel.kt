package dev.andysims.greenthumb.plants_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.andysims.greenthumb.network.NetworkStatus
import dev.andysims.greenthumb.network.Plant
import dev.andysims.greenthumb.network.TrefleApi
import kotlinx.coroutines.launch

class PlantListViewModel : ViewModel() {

    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus
        get() = _networkStatus

    private val _plants = MutableLiveData<List<Plant>>()
    val plants
        get() = _plants

    init {
        getPlants()
    }

    private fun getPlants() {
        viewModelScope.launch {
            _networkStatus.value = NetworkStatus.LOADING
            try {
                _plants.value = TrefleApi.service.getPlants().data
                _networkStatus.value = NetworkStatus.DONE
            } catch (e: Exception) {
                _plants.value = ArrayList()
                _networkStatus.value = NetworkStatus.ERROR
            }
        }
    }
}