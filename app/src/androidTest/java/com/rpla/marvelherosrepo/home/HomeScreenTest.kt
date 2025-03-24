package com.rpla.marvelherosrepo.home

import android.content.Context
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.rpla.marvelherosrepo.home.ui.HomeScreen
import com.rpla.marvelherosrepo.home.ui.viewModel.CharactersListViewModel
import com.rpla.marvelherosrepo.home.ui.viewModel.HomeIntent
import com.rpla.marvelherosrepo.home.ui.viewModel.HomeState
import com.rpla.marvelherosrepo.ui.theme.MarvelHerosRepoTheme
import com.rpla.marvelherosrepo.utils.CharactersFakeData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val characterListViewModel = mockk<CharactersListViewModel>()

    private val composeIdlingResource = com.rpla.marvelherosrepo.utils.ComposeIdlingResource()

    @Before
    fun init() {
        composeTestRule.registerIdlingResource(composeIdlingResource)
        MockKAnnotations.init(this, true)

        every { characterListViewModel.dispatchIntent(HomeIntent.AllCharacters) } returns Unit
        every { characterListViewModel.createInitialState() } returns HomeState.InitialState

        every { characterListViewModel.dispatchIntent(HomeIntent.AllCharacters) } answers {
            composeIdlingResource.isAppIdle(true)
        }
    }

    @After
    fun tearDown() {
        composeTestRule.unregisterIdlingResource(composeIdlingResource)
    }

    @Test
    fun app_bar_should_be_displayed_in_home_screen() {
        val navController = NavHostController(ApplicationProvider.getApplicationContext())
        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeScreen(
                    navigationController = navController,
                    viewModel = characterListViewModel
                )
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("HomeAppBarTitle").assertIsDisplayed()
    }

    @Test
    fun loading_items_should_be_called_when_characters_list_is_loading() {
        val mockNavController = mockk<NavHostController>(relaxed = true)
        val fakePagingItems = CharactersFakeData.getFakeLoadingPagingItems()

        every { characterListViewModel.state } returns MutableStateFlow(
            HomeState.CharactersListData(characters = fakePagingItems)
        )

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeScreen(
                    navigationController = mockNavController,
                    viewModel = characterListViewModel
                )
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onAllNodesWithTag("LoadingItem").assertCountEquals(2)
    }

    @Test
    fun characters_list_should_be_shown_on_vertical_grid() {
        val mockNavController = mockk<NavHostController>(relaxed = true)
        val fakePagingData = CharactersFakeData.getFakePagingData().flowOn(Dispatchers.IO)

        every { characterListViewModel.state } returns MutableStateFlow(
            HomeState.CharactersListData(characters = fakePagingData)
        )

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeScreen(
                    navigationController = mockNavController,
                    viewModel = characterListViewModel
                )
            }
        }

        every { characterListViewModel.state } returns MutableStateFlow(
            HomeState.CharactersListData(characters = fakePagingData)
        )

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("CharacterGrid").assertIsDisplayed()
    }

    @Test
    fun character_item_should_show_all_character_data() {
        val mockNavController = mockk<NavHostController>(relaxed = true)

        val fakeCharacter = CharactersFakeData.getFakeCharacterItem()
        val fakePagingData =
            CharactersFakeData.getFakePagingDataWithSingleCharacter(fakeCharacter)
                .flowOn(Dispatchers.IO)

        every { characterListViewModel.state } returns MutableStateFlow(
            HomeState.CharactersListData(characters = fakePagingData)
        )

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeScreen(
                    navigationController = mockNavController,
                    viewModel = characterListViewModel
                )
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("CharacterItem").assertIsDisplayed()
    }

    @Test
    fun character_item_clicked_should_navigate_to_profile_screen() {
        val mockNavController = mockk<NavHostController>(relaxed = true)
        val fakeCharacter = CharactersFakeData.getFakeCharacterItem()
        val fakePagingData = CharactersFakeData.getFakePagingDataWithSingleCharacter(fakeCharacter)
            .flowOn(Dispatchers.IO)
        val expectedRoute = "CharacterProfile?name=${fakeCharacter.id}"

        every { characterListViewModel.state } returns MutableStateFlow(
            HomeState.CharactersListData(characters = fakePagingData)
        )

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeScreen(
                    navigationController = mockNavController,
                    viewModel = characterListViewModel
                )
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("CharacterItem").performClick()
        verify { mockNavController.navigate(expectedRoute) }
    }
}