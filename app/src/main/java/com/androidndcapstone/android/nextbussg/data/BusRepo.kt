package com.androidndcapstone.android.nextbussg.data

import androidx.lifecycle.LiveData
import com.androidndcapstone.android.nextbussg.Constants
import com.androidndcapstone.android.nextbussg.data.source.remote.BusApi
import com.androidndcapstone.android.nextbussg.data.source.remote.parseBusRouteJsonResult
import com.androidndcapstone.android.nextbussg.data.source.remote.parseBusServiceJsonResult
import com.androidndcapstone.android.nextbussg.data.source.remote.parseBusStopJsonResult
import com.androidndcapstone.android.nextbussg.ui.data.*
import com.androidndcapstone.android.nextbussg.ui.data.toDbBusRouteList
import com.androidndcapstone.android.nextbussg.ui.data.toDbBusServiceList
import com.androidndcapstone.android.nextbussg.ui.data.toDbBusStopList
import com.androidndcapstone.android.nextbussg.ui.model.BusService
import com.androidndcapstone.android.nextbussg.ui.model.BusStop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.await

typealias BusStopBusService = String

class BusRepo (private val database: BusDatabase){

    fun getBusServicesByBusStopCode(busStopCode: String): LiveData<List<BusStopBusService>> {
        return database.busRouteDao.getBusServiceNumbersByBusStop(busStopCode)
    }

    suspend fun getAllBusStopsFromDatabase(): List<BusStop> {
        val dbBusStopList: List<DbBusStop>
        dbBusStopList = database.busStopDao.getAllBusStops()

        return dbBusStopList.toBusStopList()
    }

    suspend fun syncBusRoutesFromServer(){
        withContext(Dispatchers.IO)  {
            val busRouteJSONStr = BusApi.retrofitScalarService.getAllBusRoutes(Constants.ACCOUNT_KEY).await()
            val busRouteJSON = JSONObject(busRouteJSONStr)
            val busRouteList = parseBusRouteJsonResult(busRouteJSON)
            database.busRouteDao.insertAllBusRoutes(busRouteList.toList().toDbBusRouteList())
        }
    }

    suspend fun syncBusServicesFromServer(){
        withContext(Dispatchers.IO)  {
            val busServiceJSONStr = BusApi.retrofitScalarService.getAllBusServices(Constants.ACCOUNT_KEY).await()
            val busServiceJSON = JSONObject(busServiceJSONStr)
            val busServiceList = parseBusServiceJsonResult(busServiceJSON)
            database.busServiceDao.insertAllBusServices(busServiceList.toList().toDbBusServiceList())
        }
    }

    suspend fun syncBusStopsFromServer(){
        withContext(Dispatchers.IO)  {
            val busStopJSONStr = BusApi.retrofitScalarService.getAllBusStops(Constants.ACCOUNT_KEY).await()
            val busStopJSON = JSONObject(busStopJSONStr)
            val busStopList = parseBusStopJsonResult(busStopJSON)
            database.busStopDao.insertAllBusStops(busStopList.toList().toDbBusStopList())
        }
    }

}