package com.androidndcapstone.android.nextbussg.ui.model

import com.squareup.moshi.Json

data class BusArrival (
    val busArrServiceNumber: String,
    val busArrOperator: String,
    val busArrNextBus: SubsequentBus,
    val busArrNextBus2: SubsequentBus,
    val busArrNextBus3: SubsequentBus
)

data class SubsequentBus (
    val busOriginCode: String,
    val busDestinationCode: String,
    val busEstimatedArrival: String,
    val busLatitude: String,
    val busLongitude: String,
    val busVisitNumber: String,
    val busLoad: String,
    val busFeature: String,
    val busType: String
)