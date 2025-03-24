package com.rpla.marvelherosrepo.domain

import androidx.paging.PagingSource
import com.rpla.marvelherosrepo.domain.entity.base.ErrorRecord
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.home.domain.CharactersSource
import com.rpla.marvelherosrepo.home.domain.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PagingTest {

    private val mockRepository = mockk<CharacterRepository>()

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun should_get_same_amount_of_result_as_specified_in_paging_load() = runBlocking {
        coEvery { mockRepository.getCharacters(any()) } returns
                Record(FakeData.getCharactersFakeList(),
                    null)
        val pagingSource = CharactersSource(mockRepository)
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = FakeData.getCharactersFakeList().characterEntities,
                prevKey = null,
                nextKey = 2
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun should_get_error_if_server_gives_error_response() = runBlocking {
        coEvery { mockRepository.getCharacters(any()) } returns Record(null, ErrorRecord.GenericError)
        val pagingSource = CharactersSource(mockRepository)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 8,
                placeholdersEnabled = false
            )
        )

        Assert.assertTrue(result is PagingSource.LoadResult.Error)
    }
}