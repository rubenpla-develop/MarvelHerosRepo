package com.rpla.marvelherosrepo.e2e.robot

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rpla.marvelherosrepo.ui.activity.MainActivity

class CharacterDetailRobot(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    fun waitForCharactersDetailLoad(timeout: Long = 5000L): CharacterDetailRobot {
        rule.waitUntil(timeoutMillis = timeout) {
            rule.onAllNodesWithTag("profileCharacterDetails").fetchSemanticsNodes().isNotEmpty()
        }
        return this
    }

    fun verifyProfileVisible(): CharacterDetailRobot {
        rule.onNodeWithTag("profileCharacterDetails").assertIsDisplayed()
        return this
    }

    fun clickBackButton(): CharacterDetailRobot {
        rule.onNodeWithTag("profileBackButton").performClick()
        return this
    }
}
