package com.rpla.marvelherosrepo.core.di

import android.content.Context
import com.rpla.marvelherosrepo.data.DataSource
import com.rpla.marvelherosrepo.data.DataSourceImpl
import com.rpla.marvelherosrepo.remote.rest.RestApi
import com.rpla.marvelherosrepo.remote.rest.RestApiImpl
import com.rpla.marvelherosrepo.remote.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Provides
        fun providesContext(@ApplicationContext context: Context) = context

        @Provides
        fun providesRestApi(retrofitApi: RetrofitApi): RestApi = RestApiImpl(retrofitApi = retrofitApi)

        @Provides
        fun providesDataSource(restApi: RestApi): DataSource = DataSourceImpl(restApi = restApi)
}