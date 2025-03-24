package com.rpla.marvelherosrepo.domain

import com.rpla.marvelherosrepo.home.domain.entity.CharacterResultsEntity
import com.rpla.marvelherosrepo.home.domain.entity.CharactersEntity

object FakeData {
    fun getCharactersFakeList() = CharactersEntity(getCharactersListEntity())

    private fun getCharactersListEntity(): List<CharacterResultsEntity> {
            return listOf(
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                ),
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                ),
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                ),
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                ),
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                ),
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                ),
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                ),
                CharacterResultsEntity(
                    id = 1103380,
                    name = "Hero Name",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg",
                    comicsListSize = 10
                )
            )
    }
}