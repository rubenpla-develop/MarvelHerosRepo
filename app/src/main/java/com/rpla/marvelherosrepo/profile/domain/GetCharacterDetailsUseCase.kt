package com.rpla.marvelherosrepo.profile.domain

import com.rpla.marvelherosrepo.domain.base.BaseUseCase
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterDetailRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(private val characterDetailRepository: CharacterDetailRepository) :
    BaseUseCase<GetCharacterDetailsUseCase.RequestValue, Record<CharacterDetailEntity>>() {

    override suspend fun run(request: RequestValue): Record<CharacterDetailEntity> {
        return characterDetailRepository.getCharacterDetails(request.characterId)
    }

    data class RequestValue(
        val characterId: Int
    )
}