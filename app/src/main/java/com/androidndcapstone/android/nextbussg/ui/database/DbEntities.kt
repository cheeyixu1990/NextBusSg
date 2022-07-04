package com.androidndcapstone.android.nextbussg.ui.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys= [ "service_no", "direction" ], tableName = "bus_services")
data class DbBusService (
    @ColumnInfo(name = "service_no") val busServiceNumber: String,
    @ColumnInfo(name = "direction") val busDirection: String,
    @ColumnInfo(name = "operator") val busOperator: String,
    @ColumnInfo(name = "category") val busCategory: String,
    @ColumnInfo(name = "orig_stop_code") val busOriginCode: String,
    @ColumnInfo(name = "dest_stop_code") val busDestinationCode: String,
    @ColumnInfo(name = "am_peak_freq") val busAmPeakFreq: String,
    @ColumnInfo(name = "am_offpeak_freq") val busAmOffpeakFreq: String,
    @ColumnInfo(name = "pm_peak_freq") val busPmPeakFreq: String,
    @ColumnInfo(name = "pm_offpeak_freq") val busPmOffpeakFreq: String,
    @ColumnInfo(name = "loop_description") val busLoopDesc: String
)

@Entity(tableName = "bus_stops")
data class DbBusStop (
    @PrimaryKey @ColumnInfo(name = "bus_stop_code") val busStopCode: String,
    @ColumnInfo(name = "road_name") val busStopRoadName: String,
    @ColumnInfo(name = "description") val busStopDescription: String,
    @ColumnInfo(name = "latitude") val busStopLatitude: String,
    @ColumnInfo(name = "longtitude") val busStopLongitude: String
)

@Entity(primaryKeys= [ "service_no", "direction", "stop_sequence" ], tableName = "bus_routes")
data class DbBusRoute (
    @ColumnInfo(name = "service_no") val busRouteServiceNumber: String,
    @ColumnInfo(name = "direction") val busRouteDirection: String,
    @ColumnInfo(name = "stop_sequence") val busRouteStopSequence: String,
    @ColumnInfo(name = "stop_code") val busRouteStopCode: String,
    @ColumnInfo(name = "distance_from_orig") val busRouteDistance: String,
    @ColumnInfo(name = "wkday_first_bus") val wkdayFirstBus: String,
    @ColumnInfo(name = "wkday_last_bus") val wkdayLastBus: String,
    @ColumnInfo(name = "sat_first_bus") val satFirstBus: String,
    @ColumnInfo(name = "sat_last_bus") val satLastBus: String,
    @ColumnInfo(name = "sun_first_bus") val sunFirstBus: String,
    @ColumnInfo(name = "sun_last_bus") val sunLastBus: String
)