package com.rpla.marvelherosrepo.profile.data.mapper

import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterComicListEntity
import com.rpla.marvelherosrepo.profile.remote.response.CharacterComicsListResponse
import com.rpla.marvelherosrepo.profile.remote.response.toEntity

class CharacterComicsListMapper {
    fun mapCharacterComicsListResponse(characterComicsListResponse: CharacterComicsListResponse): Record<CharacterComicListEntity> {
        return Record(CharacterComicListEntity(characterComicsListResponse.data.results.toEntity()), null)
    }
}