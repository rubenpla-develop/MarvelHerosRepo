package com.rpla.marvelherosrepo.profile.domain.repository

import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity

interface CharacterDetailRepository {
    suspend fun getCharacterDetails(characterId : Int) : Record<CharacterDetailEntity>
}