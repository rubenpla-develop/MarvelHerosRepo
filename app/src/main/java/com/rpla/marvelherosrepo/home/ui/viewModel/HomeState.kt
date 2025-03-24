package com.rpla.marvelherosrepo.home.ui.viewModel

import androidx.paging.PagingData
import com.rpla.marvelherosrepo.home.domain.entity.CharacterResultsEntity
import com.rpla.marvelherosrepo.ui.base.ViewState
import kotlinx.coroutines.flow.Flow

sealed class HomeState: ViewState {

    data object InitialState: HomeState()
    data object LoadingState: HomeState()
    class CharactersListData(val characters: Flow<PagingData<CharacterResultsEntity>>): HomeState()
}
