package com.androidndcapstone.android.nextbussg.backgroudwork

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.androidndcapstone.android.nextbussg.data.BusRepo
import com.androidndcapstone.android.nextbussg.data.getDatabase
import retrofit2.HttpException

class BusDataSyncWork(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "SyncBusDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = BusRepo(database)
        return try {
            repository.syncBusStopsFromServer()
            repository.syncBusRoutesFromServer()
            repository.syncBusServicesFromServer()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

}