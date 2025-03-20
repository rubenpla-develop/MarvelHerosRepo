package com.rpla.marvelherosrepo.profile.ui.viewmodel

import com.rpla.marvelherosrepo.ui.base.ViewIntent

sealed class CharacterDetailIntent: ViewIntent {
    data object CharacterDetail: CharacterDetailIntent()
}
