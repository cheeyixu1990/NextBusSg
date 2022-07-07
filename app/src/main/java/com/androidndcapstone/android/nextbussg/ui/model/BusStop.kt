package com.androidndcapstone.android.nextbussg.ui.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusStop(
    val busStopCode: String,
    val busStopRoadName: String,
    val busStopDescription: String,
    val busStopLatitude: Double,
    val busStopLongitude: Double
): Parcelable
