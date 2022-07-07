package com.androidndcapstone.android.nextbussg.ui.data.source.remote

import com.androidndcapstone.android.nextbussg.ui.model.*
import org.json.JSONObject

fun parseBusRouteJsonResult(jsonResult: JSONObject): ArrayList<BusRoute> {
    val busRouteJsonArray = jsonResult.getJSONArray("value")

    val busRouteList = ArrayList<BusRoute>()

    for (i in 0 until busRouteJsonArray.length()) {
        val busRouteJson = busRouteJsonArray.getJSONObject(i)
        val busRouteServiceNo = busRouteJson.getString("ServiceNo")
        val busRouteOperator = busRouteJson.getString("Operator")
        val busRouteDirection = busRouteJson.getInt("Direction")
        val busRouteStopSequence = busRouteJson.getInt("StopSequence")
        val busRouteBusStopCode = busRouteJson.getString("BusStopCode")
        val busRouteDistance = busRouteJson.getDouble("Distance")
        val busRouteWD_FirstBus = busRouteJson.getString("WD_FirstBus")
        val busRouteWD_LastBus = busRouteJson.getString("WD_LastBus")
        val busRouteSAT_FirstBus = busRouteJson.getString("SAT_FirstBus")
        val busRouteSAT_LastBus = busRouteJson.getString("SAT_LastBus")
        val busRouteSUN_FirstBus = busRouteJson.getString("SUN_FirstBus")
        val busRouteSUN_LastBus = busRouteJson.getString("SUN_LastBus")

        val busRoute = BusRoute(
            busRouteServiceNumber = busRouteServiceNo,
            busRouteDirection = busRouteDirection,
            busRouteOperator = busRouteOperator,
            busRouteStopSequence = busRouteStopSequence,
            busRouteStopCode = busRouteBusStopCode,
            busRouteDistance = busRouteDistance,
            wkdayFirstBus = busRouteWD_FirstBus,
            wkdayLastBus = busRouteWD_LastBus,
            satFirstBus = busRouteSAT_FirstBus,
            satLastBus = busRouteSAT_LastBus,
            sunFirstBus = busRouteSUN_FirstBus,
            sunLastBus = busRouteSUN_LastBus
        )

        busRouteList.add(busRoute)
    }

    return busRouteList
}

fun parseBusServiceJsonResult(jsonResult: JSONObject): ArrayList<BusService> {
    val busServiceJsonArray = jsonResult.getJSONArray("value")

    val busServiceList = ArrayList<BusService>()

    for (i in 0 until busServiceJsonArray.length()) {
        val busServiceJson = busServiceJsonArray.getJSONObject(i)
        val busServiceNo = busServiceJson.getString("ServiceNo")
        val busOperator = busServiceJson.getString("Operator")
        val busDirection = busServiceJson.getInt("Direction")
        val busCategory = busServiceJson.getString("Category")
        val busOriginCode = busServiceJson.getString("OriginCode")
        val busDestinationCode = busServiceJson.getString("DestinationCode")
        val busAM_Peak_Freq = busServiceJson.getString("AM_Peak_Freq")
        val busAM_Offpeak_Freq = busServiceJson.getString("AM_Offpeak_Freq")
        val busPM_Peak_Freq = busServiceJson.getString("PM_Peak_Freq")
        val busPM_Offpeak_Freq = busServiceJson.getString("PM_Offpeak_Freq")
        val busLoopDesc = busServiceJson.getString("LoopDesc")

        val busService = BusService(
            busServiceNumber = busServiceNo,
            busDirection = busDirection,
            busOperator = busOperator,
            busCategory = busCategory,
            busOriginCode = busOriginCode,
            busDestinationCode = busDestinationCode,
            busAmPeakFreq = busAM_Peak_Freq,
            busAmOffpeakFreq = busAM_Offpeak_Freq,
            busPmPeakFreq = busPM_Peak_Freq,
            busPmOffpeakFreq = busPM_Offpeak_Freq,
            busLoopDesc = busLoopDesc
        )

        busServiceList.add(busService)
    }

    return busServiceList
}

fun parseBusStopJsonResult(jsonResult: JSONObject): ArrayList<BusStop> {
    val busStopJsonArray = jsonResult.getJSONArray("value")

    val busStopList = ArrayList<BusStop>()

    for (i in 0 until busStopJsonArray.length()) {
        val busStopJson = busStopJsonArray.getJSONObject(i)
        val busStopCode = busStopJson.getString("BusStopCode")
        val busStopRoadName = busStopJson.getString("RoadName")
        val busStopDescription = busStopJson.getString("Description")
        val busStopLatitude = busStopJson.getDouble("Latitude")
        val busStopLongitude = busStopJson.getDouble("Longitude")

        val busService = BusStop(
            busStopCode = busStopCode,
            busStopRoadName =busStopRoadName,
            busStopDescription = busStopDescription,
            busStopLatitude =busStopLatitude,
            busStopLongitude =busStopLongitude
        )

        busStopList.add(busService)
    }

    return busStopList
}

fun parseBusArrivalJsonResult(jsonResult: JSONObject): ArrayList<BusArrival> {

    val busArrivalJsonArray = jsonResult.getJSONArray("Services")

    val busArrivalList = ArrayList<BusArrival>()

    for (i in 0 until busArrivalJsonArray.length()) {
        val busStopJson = busArrivalJsonArray.getJSONObject(i)
        val busArrServiceNumber = busStopJson.getString("ServiceNo")
        val busArrOperator = busStopJson.getString("Operator")
        val busArrNextBus = busStopJson.getJSONObject("NextBus")
        val busArrNextBus2 = busStopJson.getJSONObject("NextBus2")
        val busArrNextBus3 = busStopJson.getJSONObject("NextBus3")

        val busArrival = BusArrival(
            busArrServiceNumber = busArrServiceNumber,
            busArrOperator = busArrOperator,
            busArrNextBus = SubsequentBus(
                busOriginCode = busArrNextBus.getString("OriginCode"),
                busDestinationCode = busArrNextBus.getString("DestinationCode"),
                busEstimatedArrival = busArrNextBus.getString("EstimatedArrival"),
                busLatitude = busArrNextBus.getString("Latitude"),
                busLongitude = busArrNextBus.getString("Longitude"),
                busVisitNumber = busArrNextBus.getString("VisitNumber"),
                busLoad = busArrNextBus.getString("Load"),
                busFeature = busArrNextBus.getString("Feature"),
                busType = busArrNextBus.getString("Type"),
            ),
            busArrNextBus2 = SubsequentBus(
                busOriginCode = busArrNextBus2.getString("OriginCode"),
                busDestinationCode = busArrNextBus2.getString("DestinationCode"),
                busEstimatedArrival = busArrNextBus2.getString("EstimatedArrival"),
                busLatitude = busArrNextBus2.getString("Latitude"),
                busLongitude = busArrNextBus2.getString("Longitude"),
                busVisitNumber = busArrNextBus2.getString("VisitNumber"),
                busLoad = busArrNextBus2.getString("Load"),
                busFeature = busArrNextBus2.getString("Feature"),
                busType = busArrNextBus2.getString("Type"),
            ),
            busArrNextBus3 = SubsequentBus(
                busOriginCode = busArrNextBus3.getString("OriginCode"),
                busDestinationCode = busArrNextBus3.getString("DestinationCode"),
                busEstimatedArrival = busArrNextBus3.getString("EstimatedArrival"),
                busLatitude = busArrNextBus3.getString("Latitude"),
                busLongitude = busArrNextBus3.getString("Longitude"),
                busVisitNumber = busArrNextBus3.getString("VisitNumber"),
                busLoad = busArrNextBus3.getString("Load"),
                busFeature = busArrNextBus3.getString("Feature"),
                busType = busArrNextBus3.getString("Type"),
            )
        )

        busArrivalList.add(busArrival)
    }

    return busArrivalList
}