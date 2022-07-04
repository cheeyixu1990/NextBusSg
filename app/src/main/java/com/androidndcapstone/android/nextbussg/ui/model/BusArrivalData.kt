package com.androidndcapstone.android.nextbussg.ui.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class BusArrival (
    @Json(name = "ServiceNo") val busArrServiceNumber: String,
    @Json(name = "Operator") val busArrOperator: String,
    @Json(name = "NextBus") val busArrNextBus: SubsequentBus,
    @Json(name = "NextBus2") val busArrNextBus2: SubsequentBus,
    @Json(name = "NextBus3") val busArrNextBus3: SubsequentBus
)

data class SubsequentBus (
    @Json(name = "OriginCode") val busOriginCode: String,
    @Json(name = "DestinationCode") val busDestinationCode: String,
    @Json(name = "EstimatedArrival") val busEstimatedArrival: String,
    @Json(name = "Latitude") val busLatitude: String,
    @Json(name = "Longitude") val busLongitude: String,
    @Json(name = "VisitNumber") val busVisitNumber: String,
    @Json(name = "Load") val busLoad: String,
    @Json(name = "Feature") val busFeature: String,
    @Json(name = "Type") val busType: String
)