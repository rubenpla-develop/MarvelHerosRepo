package com.rpla.marvelherosrepo.home.ui.viewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rpla.marvelherosrepo.home.domain.CharactersSource
import com.rpla.marvelherosrepo.remote.ApiConstants
import com.rpla.marvelherosrepo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val charactersSource: CharactersSource) :
    BaseViewModel<HomeIntent, HomeAction, HomeState>() {

    override fun createInitialState(): HomeState = HomeState.InitialState

    override fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.GetAllCharacters -> getAllCharacters()
            else -> {}
        }
    }

    override fun mapIntentToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.AllCharacters -> HomeAction.GetAllCharacters
            else -> HomeAction.GetAllCharacters
        }
    }


    private fun getAllCharacters() {
        val characters = Pager(PagingConfig(pageSize = ApiConstants.PAGE_SIZE)) {
            charactersSource
        }

        setState(HomeState.CharactersListData(characters = characters.flow))
    }
}