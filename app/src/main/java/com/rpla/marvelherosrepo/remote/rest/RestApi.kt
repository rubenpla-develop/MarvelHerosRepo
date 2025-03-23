package com.rpla.marvelherosrepo.remote.rest

import com.rpla.marvelherosrepo.home.remote.request.GetAllCharactersRequest
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse
import com.rpla.marvelherosrepo.profile.remote.request.GetCharacterComicsListRequest
import com.rpla.marvelherosrepo.profile.remote.request.GetCharacterDetailRequest
import com.rpla.marvelherosrepo.profile.remote.response.CharacterComicsListResponse
import com.rpla.marvelherosrepo.profile.remote.response.CharacterDetailResponse

interface RestApi {
    suspend fun getCharacters(getAllCharactersRequest: GetAllCharactersRequest): CharacterListResponse
    suspend fun getCharacterDetail(getCharacterDetailRequest: GetCharacterDetailRequest): CharacterDetailResponse
    suspend fun getCharacterComicsList(getCharacterComicsListRequest: GetCharacterComicsListRequest): CharacterComicsListResponse
}