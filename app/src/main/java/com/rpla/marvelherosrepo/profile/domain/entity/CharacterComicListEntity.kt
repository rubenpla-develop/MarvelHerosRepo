package com.rpla.marvelherosrepo.profile.domain.entity

data class CharacterComicListEntity(
    val comicsListEntities : List<ComicsListResultsEntity>
)

data class ComicsListResultsEntity(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: String
)