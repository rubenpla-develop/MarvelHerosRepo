package com.rpla.marvelherosrepo.domain

import com.rpla.marvelherosrepo.CoroutinesTestRule
import com.rpla.marvelherosrepo.domain.entity.base.Record
import com.rpla.marvelherosrepo.profile.domain.GetCharacterDetailsUseCase
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterDetailRepository
import com.rpla.marvelherosrepo.utils.CharactersFakeData
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharacterDetailsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockCharacterDetailRepository = mockk<CharacterDetailRepository>()
    private val getCharacterDetailsUseCase = GetCharacterDetailsUseCase(mockCharacterDetailRepository)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun should_get_employee_details_entity_from_repository() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockCharacterDetailRepository.getCharacterDetails(2) } answers {
            Record(CharactersFakeData.getFakeCharacterDetailEntity(), null)
        }
        getCharacterDetailsUseCase.invoke(
            coroutinesTestRule.testCoroutineScope,
            coroutinesTestRule.testDispatcher,
            GetCharacterDetailsUseCase.RequestValue(2)
        ) {
            coVerify { mockCharacterDetailRepository.getCharacterDetails(2) }
            confirmVerified(mockCharacterDetailRepository)
            Assert.assertTrue(it?.data != null)
            Assert.assertTrue(it?.error == null)
        }
    }
}