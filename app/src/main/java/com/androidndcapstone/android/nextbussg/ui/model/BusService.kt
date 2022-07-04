package com.androidndcapstone.android.nextbussg.ui.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusService(
    @Json(name = "ServiceNo") val busServiceNumber: String,
    @Json(name = "Direction") val busDirection: String,
    @Json(name = "Operator") val busOperator: String,
    @Json(name = "Category") val busCategory: String,
    @Json(name = "OriginCode") val busOriginCode: String,
    @Json(name = "DestinationCode") val busDestinationCode: String,
    @Json(name = "AM_Peak_Freq") val busAmPeakFreq: String,
    @Json(name = "AM_Offpeak_Freq") val busAmOffpeakFreq: String,
    @Json(name = "PM_Peak_Freq") val busPmPeakFreq: String,
    @Json(name = "PM_Offpeak_Freq") val busPmOffpeakFreq: String,
    @Json(name = "LoopDesc") val busLoopDesc: String
): Parcelable
