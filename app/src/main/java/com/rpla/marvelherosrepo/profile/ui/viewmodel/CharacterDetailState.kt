package com.rpla.marvelherosrepo.profile.ui.viewmodel

import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.ui.base.ViewState

sealed class CharacterDetailState: ViewState {

    data object InitialState: CharacterDetailState()
    data object LoadingState: CharacterDetailState()
    class CharacterDetailData(val characterDetail: CharacterDetailEntity?): CharacterDetailState()
}
