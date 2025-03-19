package com.rpla.marvelherosrepo.data

import com.rpla.marvelherosrepo.remote.NetworkManager

interface DataSource {
    fun api(): NetworkManager
}