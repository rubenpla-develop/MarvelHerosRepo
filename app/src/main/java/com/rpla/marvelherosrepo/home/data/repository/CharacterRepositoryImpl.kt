package com.rpla.marvelherosrepo.home.data.repository

import com.rpla.marvelherosrepo.data.DataSource
import com.rpla.marvelherosrepo.home.data.mapper.CharactersMapper
import com.rpla.marvelherosrepo.data.mapper.ErrorMapper
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.home.domain.entity.CharactersEntity
import com.rpla.marvelherosrepo.home.domain.repository.CharacterRepository
import com.rpla.marvelherosrepo.home.remote.request.GetAllCharactersRequest
import com.rpla.marvelherosrepo.remote.RemoteException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val dataSource: DataSource) :
    CharacterRepository {

    private val charactersMapper = CharactersMapper()
    private val errorMapper = ErrorMapper()


    override suspend fun getCharacters(nextPage: Int): Record<CharactersEntity> {
        return try {
            dataSource.api().restApi()
                .getCharacters(getAllCharactersRequest = GetAllCharactersRequest(nextPage)).run {
                    charactersMapper.mapCharactersResponse(this)
                }
        } catch (error : RemoteException) {
            errorMapper.mapErrorRecord(error)
        }
    }
}