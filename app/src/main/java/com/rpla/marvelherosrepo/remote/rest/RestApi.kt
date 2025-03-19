package com.rpla.marvelherosrepo.remote.rest

import com.rpla.marvelherosrepo.home.remote.request.GetAllCharactersRequest
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse

interface RestApi {
    suspend fun getCharacters(getAllCharactersRequest: GetAllCharactersRequest): CharacterListResponse

}