package com.androidndcapstone.android.nextbussg.ui.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusStop(
    @Json(name = "BusStopCode") val busStopCode: String,
    @Json(name = "RoadName") val busStopRoadName: String,
    @Json(name = "Description") val busStopDescription: String,
    @Json(name = "Latitude") val busStopLatitude: String,
    @Json(name = "Longitude") val busStopLongitude: String
): Parcelable
