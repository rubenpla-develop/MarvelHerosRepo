package com.rpla.marvelherosrepo.utils

import androidx.paging.PagingData
import com.rpla.marvelherosrepo.home.domain.entity.CharacterResultsEntity
import com.rpla.marvelherosrepo.home.remote.response.toEntity
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterComicListEntity
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.profile.domain.entity.ComicsListResultsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


object CharactersFakeData {

    fun getFakeCharacterDetailEntity(): CharacterDetailEntity {
        return CharacterDetailEntity(
            id = 1103380,
            name = "Hero Name",
            description = "Hero Description",
            thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg")
    }

    fun getFakeComicListEntity(): CharacterComicListEntity =
        CharacterComicListEntity(
            listOf(
                ComicsListResultsEntity(
                    id = 1,
                    name = "Comic Title",
                    description = "Comic Description",
                    modified = "2021-01-01",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg"
                ),
                ComicsListResultsEntity(
                    id = 1,
                    name = "Comic Title",
                    description = "Comic Description",
                    modified = "2021-01-01",
                    thumbnail = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg"
                )
            )
        )

        private fun getCharacterEntity(): List<CharacterResultsEntity> {
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