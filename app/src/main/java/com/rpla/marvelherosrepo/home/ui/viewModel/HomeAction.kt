package com.rpla.marvelherosrepo.home.ui.viewModel

import com.rpla.marvelherosrepo.ui.base.ViewAction

sealed class HomeAction: ViewAction {
    data object GetAllCharacters: HomeAction()
    data object GetFilteredCharacters: HomeAction()
}
