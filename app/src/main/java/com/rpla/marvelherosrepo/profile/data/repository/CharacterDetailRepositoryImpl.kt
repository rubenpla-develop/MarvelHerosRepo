package com.rpla.marvelherosrepo.profile.data.repository

import com.rpla.marvelherosrepo.data.DataSource
import com.rpla.marvelherosrepo.data.mapper.ErrorMapper
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.data.mapper.CharacterDetailMapper
import com.rpla.marvelherosrepo.remote.RemoteException
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.profile.remote.request.GetCharacterDetailRequest
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterDetailRepository
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(private val dataSource: DataSource) :
    CharacterDetailRepository {

    private val characterDetailMapper = CharacterDetailMapper()
    private val errorMapper = ErrorMapper()

    override suspend fun getCharacterDetails(characterId: Int): Record<CharacterDetailEntity> {
        return try {
            dataSource.api().restApi().getCharacterDetail(GetCharacterDetailRequest(characterId)).run {
                characterDetailMapper.mapCharacterDetailResponse(this)
            }
        } catch (remoteException: RemoteException) {
            errorMapper.mapErrorRecord(remoteException)
        }
    }
}