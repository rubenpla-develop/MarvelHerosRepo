package com.rpla.marvelherosrepo.home.data.mapper

import com.rpla.marvelherosrepo.home.domain.entity.CharactersEntity
import com.rpla.marvelherosrepo.home.remote.response.CharacterListResponse
import com.rpla.marvelherosrepo.home.remote.response.toEntity
import com.rpla.marvelherosrepo.domain.entity.base.Record

class CharactersMapper {

    fun mapCharactersResponse(charactersResponse: CharacterListResponse): Record<CharactersEntity> {
        return Record(CharactersEntity(charactersResponse.data.results.toEntity()), null)
    }
}