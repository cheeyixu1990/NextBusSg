package com.androidndcapstone.android.nextbussg.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.androidndcapstone.android.nextbussg.data.BusRepo
import com.androidndcapstone.android.nextbussg.data.getDatabase
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "NBSSplashViewModel"

class SplashViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val busRepo = BusRepo(database)

    private val _state = MutableLiveData<SplashState>()

    val state: LiveData<SplashState>
        get() = _state

    init {
        _state.value = SplashState(
            busRoutesLoaded = false,
            busStopsLoaded = false,
            busServicesLoaded = false
        )

        getBusRoutes()
        getBusServices()
        getBusStops()
    }

    private fun getBusRoutes() {
        Log.d(TAG, "Getting bus route")
        viewModelScope.launch {
            try {
                busRepo.syncBusRoutesFromServer()
                _state.update(busRoutesLoaded = true)
            } catch (e: HttpException) {
                Log.e(TAG, e.message())
            }
        }
    }

    private fun getBusStops() {
        Log.d(TAG, "Getting bus stops")
        viewModelScope.launch {
            try {
                busRepo.syncBusStopsFromServer()
                _state.update(busStopsLoaded = true)
            } catch (e: HttpException) {
                Log.e(TAG, e.message())
            }
        }
    }

    private fun getBusServices() {
        Log.d(TAG, "Getting bus services")
        viewModelScope.launch {
            try {
                busRepo.syncBusServicesFromServer()
                _state.update(busServicesLoaded = true)
            } catch (e: HttpException) {
                Log.e(TAG, e.message())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        resetLiveData()
    }

    private fun resetLiveData() {
        _state.update(
            busRoutesLoaded = false,
            busStopsLoaded = false,
            busServicesLoaded = false
        )
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SplashViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}


private fun MutableLiveData<SplashState>.update(
    busRoutesLoaded: Boolean = value?.busRoutesLoaded ?: false,
    busStopsLoaded: Boolean = value?.busStopsLoaded ?: false,
    busServicesLoaded: Boolean = value?.busServicesLoaded ?: false,
    exception: Exception? = value?.exception
) {
    @Suppress("CopyWithoutNamedArguments")
    value = value?.copy(busRoutesLoaded, busStopsLoaded, busServicesLoaded, exception)
}

data class SplashState(
    val busRoutesLoaded: Boolean,
    val busStopsLoaded: Boolean,
    val busServicesLoaded: Boolean,
    val exception: Exception? = null
)