package com.rpla.marvelherosrepo.home.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rpla.marvelherosrepo.home.domain.entity.CharacterResultsEntity
import com.rpla.marvelherosrepo.home.domain.repository.CharacterRepository
import javax.inject.Inject

class CharactersSource @Inject constructor(private val characterRepository: CharacterRepository) :
    PagingSource<Int, CharacterResultsEntity>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterResultsEntity>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResultsEntity> {

        val nextPage = params.key ?: 1
        val charactersResponse = characterRepository.getCharacters(nextPage)

        return if (charactersResponse.data == null) {
            LoadResult.Error(Exception(charactersResponse.error.toString()))
        } else {
            LoadResult.Page(
                data = charactersResponse.data.characterEntities,
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = nextPage.plus(1)
            )
        }
    }
}