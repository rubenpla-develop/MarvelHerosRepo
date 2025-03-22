package com.rpla.marvelherosrepo.profile.data.mapper

import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.profile.remote.response.CharacterDetailResponse
import com.rpla.marvelherosrepo.profile.remote.response.toEntity

class CharacterDetailMapper {
    fun mapCharacterDetailResponse(characterDetailResponse: CharacterDetailResponse): Record<CharacterDetailEntity> {
        return Record(
            characterDetailResponse.toEntity(),
            null
        )
    }
}