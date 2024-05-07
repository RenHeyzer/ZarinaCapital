package com.ren.forexapi.impl.di

import com.ren.forexapi.api.di.ForexBaseUrl
import com.ren.forexapi.impl.clients.AuthenticatedRetrofitClient
import com.ren.forexapi.impl.clients.DefaultRetrofitClient
import com.ren.forexapi.impl.interceptors.TokenInterceptor
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
    fun provideDefaultRetrofitClient(
        @ForexBaseUrl baseUrl: String
    ) = DefaultRetrofitClient(baseUrl)


    @Provides
    @Singleton
    fun provideAuthenticatedRetrofitClient(
        @ForexBaseUrl baseUrl: String,
        tokenInterceptor: TokenInterceptor
    ) = AuthenticatedRetrofitClient(baseUrl, tokenInterceptor)

    @Provides
    @Singleton
    fun provideAuthApiService(retrofitClient: DefaultRetrofitClient) =
        retrofitClient.authApiService
}