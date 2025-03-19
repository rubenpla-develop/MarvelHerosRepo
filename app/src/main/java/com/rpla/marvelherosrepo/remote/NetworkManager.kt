package com.rpla.marvelherosrepo.remote

import com.rpla.marvelherosrepo.remote.rest.RestApi

interface NetworkManager {
    fun restApi(): RestApi
}