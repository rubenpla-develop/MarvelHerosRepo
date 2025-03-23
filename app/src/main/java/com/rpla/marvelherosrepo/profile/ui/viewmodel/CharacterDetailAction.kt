package com.rpla.marvelherosrepo.profile.ui.viewmodel

import com.rpla.marvelherosrepo.ui.base.ViewAction

sealed class CharacterDetailAction: ViewAction {
    data object GetCharacterDetail: CharacterDetailAction()
    data object GetCharacterComicList: CharacterDetailAction()
}
