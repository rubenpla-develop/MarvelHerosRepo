package com.rpla.marvelherosrepo.utils

import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity


object CharactersFakeData {

    fun getFakeCharacterDetailEntity(): CharacterDetailEntity {
        return CharacterDetailEntity(
            id = 1103380,
            name = "Hero Name",
            description = "Hero Description",
            thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg"
        )
    }
}
