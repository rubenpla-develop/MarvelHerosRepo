package com.rpla.marvelherosrepo.e2e

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rpla.marvelherosrepo.e2e.base.BaseE2ETest
import com.rpla.marvelherosrepo.e2e.robot.HomeRobot
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterPagingFlowE2ETest: BaseE2ETest() {

//    @Test
//    fun when_scrolling_characters_list_more_items_should_load() {
//        // Verificamos que la lista inicial esté visible
//        composeTestRule.onNodeWithTag("CharacterGrid").assertIsDisplayed()
//
//        // Realizamos scroll hacia abajo en el listado
//        val characterList = composeTestRule.onNodeWithTag("CharacterGrid")
//        //characterList.performScrollTo(hasTestTag("CharacterItem_1"))
//
//        // Verificamos que los nuevos personajes sean visibles después de scrollear
//        // Aseguramos que el segundo personaje (u otro dependiendo de tu fake data) aparezca
//        composeTestRule.onNodeWithTag("CharacterItem_2").assertIsDisplayed()
//
//        // Verificamos que al menos un loading indicator para nuevos items sea visible
//        composeTestRule.onNodeWithTag("LoadingItem").assertIsDisplayed()
//    }

    @Test
    fun scroll_through_multiple_character_pages() {
        val home = HomeRobot(composeTestRule)

        home.verifyHomeVisible()
            .waitForCharacters(35000L)

        // Simulamos 2 paginaciones
        listOf(20, 40).forEach { targetIndex ->
            home.scrollToCharacterAt(targetIndex)
                .waitForCharacterAtIndex(targetIndex, 35000L)
        }

        // Validamos la visibilidad del ultimo item
        composeTestRule.onAllNodesWithTag("CharacterItem")[40].assertExists()
    }
}
