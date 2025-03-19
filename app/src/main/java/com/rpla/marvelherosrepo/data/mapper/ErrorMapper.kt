package com.rpla.marvelherosrepo.data.mapper

import android.util.Log
import com.rpla.marvelherosrepo.domain.entity.base.ErrorRecord
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.remote.RemoteException

class ErrorMapper {

    fun <T>mapErrorRecord(e: RemoteException): Record<T> {
        Log.e(ErrorMapper::class.simpleName, e.message.toString())
        val errorRecord: ErrorRecord = when(e) {
            is RemoteException.ClientError -> ErrorRecord.ClientError
            is RemoteException.ServerError -> ErrorRecord.ServerError
            is RemoteException.NoNetworkError -> ErrorRecord.NetworkError
            else -> ErrorRecord.GenericError
        }
        return Record(null, errorRecord)
    }
}