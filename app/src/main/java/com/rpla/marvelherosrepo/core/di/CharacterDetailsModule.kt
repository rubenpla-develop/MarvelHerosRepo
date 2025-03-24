package com.rpla.marvelherosrepo.core.di

import com.rpla.marvelherosrepo.profile.domain.GetCharacterComicsListUseCase
import com.rpla.marvelherosrepo.profile.domain.GetCharacterDetailsUseCase
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterComicsListRepository
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CharacterDetailsModule {

    @Provides
    fun providesGetCharacterDetailUseCase(characterDetailRepository: CharacterDetailRepository) =
        GetCharacterDetailsUseCase(characterDetailRepository)

    @Provides
    fun providesGetCharacterComicListUseCase(characterComicsListRepository: CharacterComicsListRepository) =
        GetCharacterComicsListUseCase(characterComicsListRepository)
}