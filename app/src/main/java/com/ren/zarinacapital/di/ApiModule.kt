package com.ren.zarinacapital.di

import com.ren.forexapi.RetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @ForexBaseUrl baseUrl: String
    ) = RetrofitClient(baseUrl)

    @Provides
    @Singleton
    fun provideRegisterApiService(retrofitClient: RetrofitClient) =
        retrofitClient.authApiService
}

@Qualifier
annotation class ForexBaseUrl