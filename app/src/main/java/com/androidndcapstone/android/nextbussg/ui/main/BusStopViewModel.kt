package com.androidndcapstone.android.nextbussg.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.androidndcapstone.android.nextbussg.data.BusRepo
import com.androidndcapstone.android.nextbussg.data.getDatabase
import com.androidndcapstone.android.nextbussg.ui.model.BusStop
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "NBSBusStopViewModel"

class BusStopViewModel (application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val busRepo = BusRepo(database)

    private val _busStops = MutableLiveData<List<BusStop>>()
    val busStops : LiveData<List<BusStop>>
        get() = _busStops

    init {
        viewModelScope.launch {
            _busStops.value = busRepo.getAllBusStopsFromDatabase()
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BusStopViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BusStopViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}