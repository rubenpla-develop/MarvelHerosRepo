package com.rpla.marvelherosrepo.profile.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.rpla.marvelherosrepo.profile.domain.GetCharacterComicsListUseCase
import com.rpla.marvelherosrepo.profile.domain.GetCharacterDetailsUseCase
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterComicListEntity
import com.rpla.marvelherosrepo.ui.base.BaseViewModel
import com.rpla.marvelherosrepo.ui.navigation.DEFAULT_CHARACTER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getCharacterComicsListUseCase: GetCharacterComicsListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    BaseViewModel<ProfileScreenIntent, CharacterDetailAction, CharacterDetailState>() {

    private var characterId = DEFAULT_CHARACTER_ID
    var characterComicList: CharacterComicListEntity? = null

    override fun createInitialState(): CharacterDetailState = CharacterDetailState.InitialState

    override fun handleAction(action: CharacterDetailAction) {
        when (action) {
            is CharacterDetailAction.GetCharacterDetail -> {
                getCharacterDetail(characterId)
            }

            is CharacterDetailAction.GetCharacterComicList -> {
                getCharacterComicList(characterId)
            }
        }
    }

    override fun mapIntentToAction(intent: ProfileScreenIntent): CharacterDetailAction {
        return when (intent) {
            is ProfileScreenIntent.CharacterDetail -> CharacterDetailAction.GetCharacterDetail
            is ProfileScreenIntent.CharacterComicList -> CharacterDetailAction.GetCharacterComicList
        }
    }

    fun setCharacterId(id : Int) {
        characterId = id
    }

    fun setComicList(list: CharacterComicListEntity?) {
        characterComicList = list
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

    private fun getCharacterComicList(characterId: Int) {
        getCharacterComicsListUseCase.invoke(
            viewModelScope,
            dispatcher,
            GetCharacterComicsListUseCase.RequestValue(characterId = characterId)
        ) { record ->
            setState(CharacterDetailState.CharacterComicListData(characterComicList = record?.data))
        }
    }
}