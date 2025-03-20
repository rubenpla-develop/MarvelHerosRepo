package com.rpla.marvelherosrepo.profile.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.rpla.marvelherosrepo.profile.domain.GetCharacterDetailsUseCase
import com.rpla.marvelherosrepo.ui.base.BaseViewModel
import com.rpla.marvelherosrepo.ui.navigation.DEFAULT_CHARACTER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    BaseViewModel<CharacterDetailIntent, CharacterDetailAction, CharacterDetailState>() {

    private var characterId = DEFAULT_CHARACTER_ID

    override fun createInitialState(): CharacterDetailState = CharacterDetailState.InitialState

    override fun handleAction(action: CharacterDetailAction) {
        when (action) {
            is CharacterDetailAction.GetCharacterDetail -> {
                getCharacterDetail(characterId)
            }

            else -> {}
        }
    }

    override fun mapIntentToAction(intent: CharacterDetailIntent): CharacterDetailAction {
        return when (intent) {
            is CharacterDetailIntent.CharacterDetail -> CharacterDetailAction.GetCharacterDetail
            else -> CharacterDetailAction.GetCharacterDetail
        }
    }

    fun setCharacterId(id : Int) {
        characterId = id
    }


    private fun getCharacterDetail(characterId: Int) {
        getCharacterDetailsUseCase.invoke(
            viewModelScope,
            dispatcher,
            GetCharacterDetailsUseCase.RequestValue(characterId = characterId)
        ) { record ->
            setState(CharacterDetailState.CharacterDetailData(characterDetail = record?.data))
        }
    }
}