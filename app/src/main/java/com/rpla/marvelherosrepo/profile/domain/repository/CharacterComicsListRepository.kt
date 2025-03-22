package com.rpla.marvelherosrepo.profile.domain.repository

import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterComicListEntity

interface CharacterComicsListRepository {
    suspend fun getCharacterComicsList(characterId : Int) : Record<CharacterComicListEntity>
}