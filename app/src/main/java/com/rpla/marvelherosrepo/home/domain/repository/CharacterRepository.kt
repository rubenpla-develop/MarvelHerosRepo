package com.rpla.marvelherosrepo.home.domain.repository

import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.home.domain.entity.CharactersEntity

interface CharacterRepository {
    suspend fun getCharacters(nextPage: Int) : Record<CharactersEntity>
}