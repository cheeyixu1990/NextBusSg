package com.androidndcapstone.android.nextbussg.ui.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusRoute(
    @Json(name = "ServiceNo") val busRouteServiceNumber: String,
    @Json(name = "Direction") val busRouteDirection: String,
    @Json(name = "StopSequence") val busRouteStopSequence: String,
    @Json(name = "BusStopCode") val busRouteStopCode: String,
    @Json(name = "Distance") val busRouteDistance: String,
    @Json(name = "WD_FirstBus") val wkdayFirstBus: String,
    @Json(name = "WD_LastBus") val wkdayLastBus: String,
    @Json(name = "SAT_FirstBus") val satFirstBus: String,
    @Json(name = "SAT_LastBus") val satLastBus: String,
    @Json(name = "SUN_FirstBus") val sunFirstBus: String,
    @Json(name = "SUN_LastBus") val sunLastBus: String
) : Parcelable
