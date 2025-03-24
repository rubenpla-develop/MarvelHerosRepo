package com.rpla.marvelherosrepo.core.di

import android.content.Context
import com.rpla.marvelherosrepo.data.DataSource
import com.rpla.marvelherosrepo.home.data.repository.CharacterRepositoryImpl
import com.rpla.marvelherosrepo.home.domain.repository.CharacterRepository
import com.rpla.marvelherosrepo.profile.data.repository.CharacterComicsListRepositoryImpl
import com.rpla.marvelherosrepo.profile.data.repository.CharacterDetailRepositoryImpl
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterComicsListRepository
import com.rpla.marvelherosrepo.profile.domain.repository.CharacterDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesCharacterRepository(dataSource: DataSource, context: Context): CharacterRepository =
        CharacterRepositoryImpl(dataSource = dataSource,
            context = context)

    @Provides
    fun providesCharacterDetailRepository(dataSource: DataSource): CharacterDetailRepository =
        CharacterDetailRepositoryImpl(dataSource = dataSource)

    @Provides
    fun providesCharacterComicsListRepository(dataSource: DataSource): CharacterComicsListRepository =
        CharacterComicsListRepositoryImpl(dataSource = dataSource)
}