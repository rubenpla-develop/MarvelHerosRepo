package com.rpla.marvelherosrepo.profile.domain

import com.rpla.marvelherosrepo.domain.base.BaseUseCase
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterComicListEntity
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterComicsListRepository
import javax.inject.Inject

class GetCharacterComicsListUseCase @Inject constructor(private val characterComicsListRepository: CharacterComicsListRepository) :
    BaseUseCase<GetCharacterComicsListUseCase.RequestValue, Record<CharacterComicListEntity>>() {

    override suspend fun run(request: RequestValue): Record<CharacterComicListEntity> {
        return characterComicsListRepository.getCharacterComicsList(request.characterId)
    }

    data class RequestValue(
        val characterId: Int
    )
}