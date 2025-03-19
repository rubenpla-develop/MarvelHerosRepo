package com.rpla.marvelherosrepo.remote

import com.rpla.marvelherosrepo.remote.rest.RestApi
import javax.inject.Inject

class NetworkManagerImpl @Inject constructor(private val restApi: RestApi): NetworkManager {

    override fun restApi(): RestApi = restApi
}