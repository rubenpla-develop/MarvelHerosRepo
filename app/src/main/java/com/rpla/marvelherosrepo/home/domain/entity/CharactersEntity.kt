package com.rpla.marvelherosrepo.home.domain.entity

data class CharactersEntity(
    val characterEntities : List<CharacterResultsEntity>
)

data class CharacterResultsEntity(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val comicsListSize: Int,
)