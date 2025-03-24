package com.rpla.marvelherosrepo.e2e.robot

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rpla.marvelherosrepo.ui.activity.MainActivity

class HomeRobot(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    fun waitForCharacters(timeout: Long = 5000L): HomeRobot {
        rule.waitUntil(timeoutMillis = timeout) {
            rule.onAllNodesWithTag("CharacterItem").fetchSemanticsNodes().isNotEmpty()
        }
        return this
    }

    fun clickOnCharacterAt(index: Int): HomeRobot {
        rule.onAllNodesWithTag("CharacterItem")[index].performClick()
        return this
    }

    fun verifyHomeVisible(): HomeRobot {
        rule.onNodeWithTag("HomeAppBarTitle").assertIsDisplayed()
        return this
    }

    fun scrollToCharacterAt(index: Int): HomeRobot {
        rule.onNodeWithTag("CharacterGrid")
            .performScrollToIndex(index)
        return this
    }

    fun waitForCharacterAtIndex(index: Int, timeout: Long = 5000L): HomeRobot {
        rule.waitUntil(timeoutMillis = timeout) {
            rule.onAllNodesWithTag("CharacterItem").fetchSemanticsNodes().size > index
        }
        return this
    }
}
