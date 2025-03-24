package com.rpla.marvelherosrepo.e2e.base

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rpla.marvelherosrepo.ui.activity.MainActivity
import org.junit.Rule

abstract class BaseE2ETest {
    @get:Rule
    val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity> =
        createAndroidComposeRule()
}
