package com.androidndcapstone.android.nextbussg.ui.data.source.remote

import com.androidndcapstone.android.nextbussg.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private const val TAG = "NBSGBusApiService"

private val BASE_URL = Constants.BASE_URL

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitJson = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private val retrofitScalar = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BusApiService {
    @GET(Constants.BUS_ROUTE_API_PATH)
    fun getAllBusRoutes(@Header("AccountKey") accountKey: String): Call<String>

    @GET(Constants.BUS_STOP_API_PATH)
    fun getAllBusStops(@Header("AccountKey") accountKey: String): Call<String>

    @GET(Constants.BUS_SERVICE_API_PATH)
    fun getAllBusServices(@Header("AccountKey") accountKey: String): Call<String>

    @GET(Constants.BUS_ARRIVAL_API_PATH)
    fun getBusArrival(@Header("AccountKey") accountKey: String, @Query("BusStopCode") busStopCode: String): Call<String>

}

object BusApi {
    val retrofitJsonService : BusApiService by lazy {
        retrofitJson.create(BusApiService::class.java)
    }

    val retrofitScalarService : BusApiService by lazy {
        retrofitScalar.create(BusApiService::class.java)
    }
}