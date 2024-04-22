package com.ren.forexapi.impl.di

import com.ren.forexapi.api.di.ForexBaseUrl
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
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

    @Provides
    @Singleton
    fun provideNewsApiService(retrofitClient: RetrofitClient) =
        retrofitClient.newsApiService
}