package com.rpla.marvelherosrepo

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.rpla.marvelherosrepo.ui.common.HomeAppBar
import com.rpla.marvelherosrepo.ui.theme.MarvelHerosRepoTheme
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test

class HomeAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun given_title_is_shown_in_home_app_bar() {
        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeAppBar(
                    title = stringResource(R.string.app_name),
                    modifier = Modifier,
                    openFilters = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("HomeAppBarTitle").assertIsDisplayed()
    }

    @Test
    fun given_filter_icon_is_shown_in_home_app_bar() {
        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeAppBar(
                    title = stringResource(R.string.app_name),
                    modifier = Modifier,
                    openFilters = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("HomeAppBarFilterButton").assertIsDisplayed()
    }

    @Test
    fun given_filter_button_is_clicked_toast_message_appears() {
        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeAppBar(
                    title = stringResource(R.string.app_name),
                    modifier = Modifier,
                    openFilters = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("HomeAppBarFilterButton").performClick()
    }

    @Test
    fun given_filter_button_is_clicked_lambda_function_is_called() {
        var filterClicked = false

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeAppBar(
                    title = stringResource(R.string.app_name),
                    modifier = Modifier,
                    openFilters = { filterClicked = true }
                )
            }
        }

        composeTestRule.onNodeWithTag("HomeAppBarFilterButton").performClick()

        assertThat(filterClicked, `is`(true))
    }

    @Test
    fun top_app_bar_is_displayed_with_correct_title_text() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val title = context.getString(R.string.app_name)

        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeAppBar(
                    title = title,
                    modifier = Modifier,
                    openFilters = {}
                )
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun top_app_bar_contains_title_and_filter_button_using_test_tags() {
        composeTestRule.setContent {
            MarvelHerosRepoTheme {
                HomeAppBar(
                    title = stringResource(R.string.app_name),
                    modifier = Modifier,
                    openFilters = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("HomeAppBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("HomeAppBarTitle").assertIsDisplayed()
        composeTestRule.onNodeWithTag("HomeAppBarFilterButton").assertIsDisplayed()
    }
}
