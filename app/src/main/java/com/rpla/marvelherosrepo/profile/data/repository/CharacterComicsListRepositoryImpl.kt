package com.rpla.marvelherosrepo.profile.data.repository

import com.rpla.marvelherosrepo.data.DataSource
import com.rpla.marvelherosrepo.data.mapper.ErrorMapper
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.data.mapper.CharacterComicsListMapper
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterComicListEntity
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterComicsListRepository
import com.rpla.marvelherosrepo.profile.remote.request.GetCharacterComicsListRequest
import com.rpla.marvelherosrepo.remote.RemoteException
import javax.inject.Inject

class CharacterComicsListRepositoryImpl @Inject constructor(private val dataSource: DataSource) :
    CharacterComicsListRepository {

    private val characterComicsListMapper = CharacterComicsListMapper()
    private val errorMapper = ErrorMapper()

    override suspend fun getCharacterComicsList(characterId: Int): Record<CharacterComicListEntity> {
        return try {
            dataSource.api().restApi().getCharacterComicsList(GetCharacterComicsListRequest(characterId)).run {
                characterComicsListMapper.mapCharacterComicsListResponse(this)
            }
        } catch (remoteException: RemoteException) {
            errorMapper.mapErrorRecord(remoteException)
        }
    }
}