package com.rpla.marvelherosrepo.remote.rest

import com.rpla.marvelherosrepo.home.remote.request.GetAllCharactersRequest
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse
import com.rpla.marvelherosrepo.profile.remote.request.GetCharacterComicsListRequest
import com.rpla.marvelherosrepo.profile.remote.request.GetCharacterDetailRequest
import com.rpla.marvelherosrepo.profile.remote.response.CharacterComicsListResponse
import com.rpla.marvelherosrepo.profile.remote.response.CharacterDetailResponse
import com.rpla.marvelherosrepo.remote.retrofit.RetrofitApi
import javax.inject.Inject

class RestApiImpl @Inject constructor(private val retrofitApi: RetrofitApi): RestApi {

    override suspend fun getCharacters(getAllCharactersRequest: GetAllCharactersRequest): CharacterListResponse {
        return retrofitApi.getCharactersList(offset = getAllCharactersRequest.nextPage)
    }

    override suspend fun getCharacterDetail(getCharacterDetailRequest: GetCharacterDetailRequest): CharacterDetailResponse {
        return retrofitApi.getCharacterDetail(getCharacterDetailRequest.id)
    }

    override suspend fun getCharacterComicsList(getCharacterComicsListRequest: GetCharacterComicsListRequest): CharacterComicsListResponse {
        return retrofitApi.getCharacterComicsList(getCharacterComicsListRequest.id)
    }
}