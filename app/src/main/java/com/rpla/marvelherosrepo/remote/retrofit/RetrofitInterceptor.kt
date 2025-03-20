package com.rpla.marvelherosrepo.remote.retrofit

import com.rpla.marvelherosrepo.remote.ApiConstants
import com.rpla.marvelherosrepo.remote.RemoteException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RetrofitInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val requestBuilder = chain.request().newBuilder()
            val url = chain.request().url

            requestBuilder.url(url)
            val response = chain.proceed(requestBuilder.build())
            when (response.code) {
                in HttpURLConnection.HTTP_UNAUTHORIZED..ApiConstants.STATUS_CODE_401_INVALID_REFERRER_OR_HASH -> {
                    throw RemoteException.ServerError(response.message)
                }
                in HttpURLConnection.HTTP_BAD_METHOD..ApiConstants.STATUS_CODE_405_METHOD_NOT_ALLOWED -> {
                    throw RemoteException.ServerError(response.message)
                }
                in HttpURLConnection.HTTP_FORBIDDEN..ApiConstants.STATUS_CODE_403_FORBIDDEN -> {
                    throw RemoteException.ServerError(response.message)
                }
                in HttpURLConnection.HTTP_CONFLICT..ApiConstants.STATUS_CODE_409_MISSING_QUERY_PARAM -> {
                    throw RemoteException.ClientError(response.message)
                }
                in HttpURLConnection.HTTP_INTERNAL_ERROR..ApiConstants.STATUS_CODE_500_INTERNAL_ERROR -> {
                    throw RemoteException.GenericError(response.message)
                }
                else -> return response
            }
        } catch (e: Exception) {
            throw  when (e) {
                is UnknownHostException -> RemoteException.NoNetworkError(e.message.toString())
                is SocketTimeoutException -> RemoteException.NoNetworkError(e.message.toString())
                is RemoteException -> e
                else -> RemoteException.GenericError(e.message.toString())
            }
        }
    }
}