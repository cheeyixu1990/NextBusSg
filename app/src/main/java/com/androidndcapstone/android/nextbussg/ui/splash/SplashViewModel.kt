package com.androidndcapstone.android.nextbussg.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.androidndcapstone.android.nextbussg.data.BusRepo
import com.androidndcapstone.android.nextbussg.data.getDatabase
import com.androidndcapstone.android.nextbussg.ui.model.BusRoute
import com.androidndcapstone.android.nextbussg.ui.model.BusService
import com.androidndcapstone.android.nextbussg.ui.model.BusStop
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "NBSSplashViewModel"

class SplashViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val busRepo = BusRepo(database)

    private val _state = MutableLiveData<SplashState>()
    private val _busRoutes = MutableLiveData<List<BusRoute>>()
    private val _busStops = MutableLiveData<List<BusStop>>()
    private val _busServices = MutableLiveData<List<BusService>>()

    val state: LiveData<SplashState>
        get() = _state
    val busRoutes: LiveData<List<BusRoute>>
        get() = _busRoutes
    val busStops: LiveData<List<BusStop>>
        get() = _busStops
    val busServices: LiveData<List<BusService>>
        get() = _busServices

    init {
        _state.value = SplashState(
            loading = false
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
            } catch (e: HttpException) {
                Log.e(TAG, e.message())
            }
        }    }

    private fun getBusServices() {
        Log.d(TAG, "Getting bus services")
        viewModelScope.launch {
            try {
                busRepo.syncBusServicesFromServer()
            } catch (e: HttpException) {
                Log.e(TAG, e.message())
            }
        }    }

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
    loading: Boolean = value?.loading ?: false,
    exception: Exception? = value?.exception
) {
    @Suppress("CopyWithoutNamedArguments")
    value = value?.copy(loading, exception)
}

data class SplashState(
    val loading: Boolean,
    val exception: Exception? = null
)