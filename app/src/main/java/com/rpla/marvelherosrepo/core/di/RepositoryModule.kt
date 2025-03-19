package com.rpla.marvelherosrepo.core.di

import com.rpla.marvelherosrepo.data.DataSource
import com.rpla.marvelherosrepo.home.data.repository.CharacterRepositoryImpl
import com.rpla.marvelherosrepo.home.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesCharacterRepository(dataSource: DataSource): CharacterRepository =
        CharacterRepositoryImpl(dataSource = dataSource)

}