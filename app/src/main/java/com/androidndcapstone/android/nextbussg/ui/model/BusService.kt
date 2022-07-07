package com.androidndcapstone.android.nextbussg.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusService(
    val busServiceNumber: String,
    val busDirection: Int,
    val busOperator: String,
    val busCategory: String,
    val busOriginCode: String,
    val busDestinationCode: String,
    val busAmPeakFreq: String,
    val busAmOffpeakFreq: String,
    val busPmPeakFreq: String,
    val busPmOffpeakFreq: String,
    val busLoopDesc: String
): Parcelable
