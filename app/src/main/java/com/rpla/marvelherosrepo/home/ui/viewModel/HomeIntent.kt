package com.rpla.marvelherosrepo.home.ui.viewModel

import com.rpla.marvelherosrepo.ui.base.ViewIntent

sealed class HomeIntent: ViewIntent {
    data object AllCharacters: HomeIntent()
    data object GetFilteredCharacters: HomeIntent()
}
