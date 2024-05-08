package com.ren.datastore.impl.di

import com.ren.common.Mapper
import com.ren.datastore.impl.TokenDataStore
import com.ren.datastore.api.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreModule {

    @Binds
    @Singleton
    fun bindTokenManager(dataStore: TokenDataStore): TokenManager
}