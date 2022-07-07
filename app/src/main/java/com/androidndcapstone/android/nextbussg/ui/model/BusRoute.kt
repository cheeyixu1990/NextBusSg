package com.androidndcapstone.android.nextbussg.ui.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusRoute(
    val busRouteServiceNumber: String,
    val busRouteOperator: String,
    val busRouteDirection: Int,
    val busRouteStopSequence: Int,
    val busRouteStopCode: String,
    val busRouteDistance: Double,
    val wkdayFirstBus: String,
    val wkdayLastBus: String,
    val satFirstBus: String,
    val satLastBus: String,
    val sunFirstBus: String,
    val sunLastBus: String
) : Parcelable
