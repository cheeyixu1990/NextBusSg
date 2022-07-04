package com.androidndcapstone.android.nextbussg.ui.database

import com.androidndcapstone.android.nextbussg.ui.model.BusRoute
import com.androidndcapstone.android.nextbussg.ui.model.BusService
import com.androidndcapstone.android.nextbussg.ui.model.BusStop

// Bus route
internal fun BusRoute.toDbBusRoute() = DbBusRoute(
    busRouteServiceNumber, busRouteDirection, busRouteStopSequence, busRouteStopCode, busRouteDistance, wkdayFirstBus, wkdayLastBus, satFirstBus, satLastBus, sunFirstBus, sunLastBus
)

internal fun DbBusRoute.toBusRoute() = BusRoute(
    busRouteServiceNumber, busRouteDirection, busRouteStopSequence, busRouteStopCode, busRouteDistance, wkdayFirstBus, wkdayLastBus, satFirstBus, satLastBus, sunFirstBus, sunLastBus
)

internal fun List<BusRoute>.toDbBusRouteList() = map {
    it.toDbBusRoute()
}

internal fun List<DbBusRoute>.toBusRouteList() = map {
    it.toBusRoute()
}

// Bus stop
internal fun BusStop.toDbBusStop() = DbBusStop(
    busStopCode, busStopRoadName, busStopDescription, busStopLatitude, busStopLongitude
)

internal fun DbBusStop.toBusStop() = BusStop(
    busStopCode, busStopRoadName, busStopDescription, busStopLatitude, busStopLongitude
)

internal fun List<BusStop>.toDbBusStopList() = map {
    it.toDbBusStop()
}

internal fun List<DbBusStop>.toBusStopList() = map {
    it.toBusStop()
}

// Bus service
internal fun BusService.toDbBusService() = DbBusService(
    busServiceNumber, busDirection, busOperator, busCategory, busOriginCode, busDestinationCode, busAmPeakFreq, busAmOffpeakFreq, busPmPeakFreq, busPmOffpeakFreq, busLoopDesc
)

internal fun DbBusService.toBusService() = BusService(
    busServiceNumber, busDirection, busOperator, busCategory, busOriginCode, busDestinationCode, busAmPeakFreq, busAmOffpeakFreq, busPmPeakFreq, busPmOffpeakFreq, busLoopDesc
)

internal fun List<BusService>.toDbBusServiceList() = map {
    it.toDbBusService()
}

internal fun List<DbBusService>.toBusServiceList() = map {
    it.toBusService()
}