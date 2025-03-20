package com.rpla.marvelherosrepo.core.di

import com.rpla.marvelherosrepo.home.domain.CharactersSource
import com.rpla.marvelherosrepo.home.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeScreenModule {

    @Provides
    fun providesCharactersSource(characterRepository: CharacterRepository) =
        CharactersSource(characterRepository)
}