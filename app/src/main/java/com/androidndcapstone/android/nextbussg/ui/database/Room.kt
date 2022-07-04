/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.androidndcapstone.android.nextbussg.ui.database

import android.content.Context
import androidx.room.*

@Dao
interface BusRouteDao {
    @Query("select * from bus_routes")
    suspend fun getAllBusRoute(): List<DbBusRoute>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBusRoute(list: List<DbBusRoute>)
}

@Database(entities = [DbBusRoute::class, DbBusService::class, DbBusStop::class], version = 1)
abstract class BusDatabase : RoomDatabase() {
    abstract val busRouteDao: BusRouteDao
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