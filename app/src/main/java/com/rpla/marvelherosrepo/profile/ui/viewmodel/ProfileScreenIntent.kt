package com.rpla.marvelherosrepo.profile.ui.viewmodel

import com.rpla.marvelherosrepo.ui.base.ViewIntent

sealed class ProfileScreenIntent: ViewIntent {
    data object CharacterDetail: ProfileScreenIntent()
    data object CharacterComicList: ProfileScreenIntent()
}
