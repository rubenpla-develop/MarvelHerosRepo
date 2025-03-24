package com.rpla.marvelherosrepo.e2e

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rpla.marvelherosrepo.e2e.base.BaseE2ETest
import com.rpla.marvelherosrepo.e2e.robot.CharacterDetailRobot
import com.rpla.marvelherosrepo.e2e.robot.HomeRobot
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDetailsNavigationE2ETest: BaseE2ETest() {

        @Test
        fun navigate_from_home_to_profile_and_back() {
            val home = HomeRobot(composeTestRule)
            val profile = CharacterDetailRobot(composeTestRule)

            home.verifyHomeVisible()
                .waitForCharacters(35000L) //Due to API down
                .clickOnCharacterAt(0)

            profile.waitForCharactersDetailLoad(35000L) //Due to API down
                .verifyProfileVisible()
                .clickBackButton()

            home.verifyHomeVisible()
        }
}
