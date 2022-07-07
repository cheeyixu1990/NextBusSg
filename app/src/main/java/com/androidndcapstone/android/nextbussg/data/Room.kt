package com.androidndcapstone.android.nextbussg.data

import android.content.Context
import androidx.room.*
import com.androidndcapstone.android.nextbussg.ui.data.DbBusRoute
import com.androidndcapstone.android.nextbussg.ui.data.DbBusService
import com.androidndcapstone.android.nextbussg.ui.data.DbBusStop

@Dao
interface BusRouteDao {
    @Query("select * from bus_routes")
    suspend fun getAllBusRoutes(): List<DbBusRoute>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBusRoutes(list: List<DbBusRoute>)
}

@Dao
interface BusServiceDao {
    @Query("select * from bus_services")
    suspend fun getAllBusServices(): List<DbBusService>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBusServices(list: List<DbBusService>)
}

@Dao
interface BusStopDao {
    @Query("select * from bus_stops")
    suspend fun getAllBusStops(): List<DbBusStop>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBusStops(list: List<DbBusStop>)
}

@Database(entities = [DbBusRoute::class, DbBusService::class, DbBusStop::class], version = 1)
abstract class BusDatabase : RoomDatabase() {
    abstract val busRouteDao: BusRouteDao
    abstract val busServiceDao: BusServiceDao
    abstract val busStopDao: BusStopDao
}

private lateinit var INSTANCE: BusDatabase

fun getDatabase(context: Context): BusDatabase {
    synchronized(BusDatabase::class.java)    {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                BusDatabase::class.java,
                "bus.db"
            ).build()
        }
    }
    return INSTANCE
}