package com.rpla.marvelherosrepo.data

import com.rpla.marvelherosrepo.remote.NetworkManager
import com.rpla.marvelherosrepo.remote.NetworkManagerImpl
import com.rpla.marvelherosrepo.remote.rest.RestApi

import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val restApi: RestApi): DataSource {
    override fun api(): NetworkManager = NetworkManagerImpl(restApi = restApi)
}