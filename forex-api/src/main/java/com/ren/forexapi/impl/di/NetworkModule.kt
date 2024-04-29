package com.ren.forexapi.impl.di

import com.ren.forexapi.api.di.ForexBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @ForexBaseUrl baseUrl: String
    ) = RetrofitClient(baseUrl)

    @Provides
    @Singleton
    fun provideAuthApiService(retrofitClient: RetrofitClient) =
        retrofitClient.authApiService
}