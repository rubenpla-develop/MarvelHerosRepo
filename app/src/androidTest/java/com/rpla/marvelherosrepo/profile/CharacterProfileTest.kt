package com.rpla.marvelherosrepo.profile

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import com.rpla.marvelherosrepo.profile.ui.ProfileScreen
import com.rpla.marvelherosrepo.profile.ui.viewmodel.CharacterDetailState
import com.rpla.marvelherosrepo.profile.ui.viewmodel.CharacterDetailViewModel
import com.rpla.marvelherosrepo.profile.ui.viewmodel.ProfileScreenIntent
import com.rpla.marvelherosrepo.ui.navigation.Routes
import com.rpla.marvelherosrepo.ui.theme.MarvelHerosRepoTheme
import com.rpla.marvelherosrepo.utils.CharactersFakeData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterProfileTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeComicListItems = CharactersFakeData.getFakeComicListEntity()
    private val fakeCharacterDetails = CharactersFakeData.getFakeCharacterDetailEntity()
    private val fakeCharacter = CharactersFakeData.getFakeCharacterItem()

    private val characterDetailViewModel = mockk<CharacterDetailViewModel>()

    private val composeIdlingResource = com.rpla.marvelherosrepo.utils.ComposeIdlingResource()

    @Before
    fun init() {
        composeTestRule.registerIdlingResource(composeIdlingResource)
        MockKAnnotations.init(this, true)

        every { characterDetailViewModel.dispatchIntent(ProfileScreenIntent.CharacterDetail) } returns Unit
        every { characterDetailViewModel.dispatchIntent(ProfileScreenIntent.CharacterComicList) } returns Unit
        every { characterDetailViewModel.createInitialState() } returns CharacterDetailState.InitialState

        every { characterDetailViewModel.dispatchIntent(ProfileScreenIntent.CharacterDetail) } answers {
            composeIdlingResource.isAppIdle(true)
        }
        every { characterDetailViewModel.dispatchIntent(ProfileScreenIntent.CharacterComicList) } answers {
            composeIdlingResource.isAppIdle(true)
        }

        every { characterDetailViewModel.setCharacterId(any()) } returns Unit
        every { characterDetailViewModel.setComicList(any()) } returns Unit
    }

    @After
    fun tearDown() {
        composeTestRule.unregisterIdlingResource(composeIdlingResource)
    }

    @Test
    fun app_bar_should_be_displayed_in_home_screen() {
        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                ProfileScreen (
                    viewModel = characterDetailViewModel,
                    characterId = 1,

                ) {}
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("HomeAppBarTitle").assertIsDisplayed()
    }

    @Test
    fun loading_item_should_be_called_when_characters_comic_list_is_loading() {
        every { characterDetailViewModel.state } returns MutableStateFlow(
            CharacterDetailState.CharacterComicListData(characterComicList = fakeComicListItems)
        )

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                ProfileScreen(
                    viewModel = characterDetailViewModel,
                    characterId = fakeCharacter.id
                ) {}
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onAllNodesWithTag("LoadingItem").assertCountEquals(1)
    }

    @Test
    fun character_details_should_be_shown() {
        every { characterDetailViewModel.state } returns MutableStateFlow(
            CharacterDetailState.CharacterDetailData(characterDetail = fakeCharacterDetails)
        )
        every { characterDetailViewModel.characterComicList } returns null

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                ProfileScreen(
                    viewModel = characterDetailViewModel,
                    characterId = fakeCharacter.id
                ) {}
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("profileCharacterDetails").assertIsDisplayed()
    }

    @Test
    fun comics_list_should_be_shown() {
        every { characterDetailViewModel.state } returns MutableStateFlow(
            CharacterDetailState.CharacterDetailData(characterDetail = fakeCharacterDetails)
        )

        every { characterDetailViewModel.characterComicList } returns fakeComicListItems

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                ProfileScreen(
                    viewModel = characterDetailViewModel,
                    characterId = fakeCharacter.id
                ) {}
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("profileComicList").assertIsDisplayed()
    }

    @Test
    fun profile_screen_back_button_should_navigate_to_home_screen() {
        val mockNavController = mockk<NavHostController>(relaxed = true)
        val expectedRoute = Routes.HomeScreen.route

        every { characterDetailViewModel.state } returns MutableStateFlow(
            CharacterDetailState.CharacterDetailData(characterDetail = fakeCharacterDetails)
        )

        every { characterDetailViewModel.characterComicList } returns fakeComicListItems

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                ProfileScreen(
                    viewModel = characterDetailViewModel,
                    characterId = fakeCharacter.id
                ) { mockNavController.navigate(Routes.HomeScreen.route) }
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("profileBackButton").performClick()
        verify { mockNavController.navigate(expectedRoute) }
    }
}