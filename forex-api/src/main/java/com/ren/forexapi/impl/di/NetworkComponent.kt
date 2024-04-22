package com.ren.forexapi.impl.di

import com.ren.forexapi.api.apiservice.auth.AuthApiService
import com.ren.forexapi.api.apiservice.news.NewsApiService
import com.ren.forexapi.api.di.ForexBaseUrl
import com.ren.forexapi.api.di.NetworkApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@[Singleton Component(modules = [NetworkModule::class])]
interface NetworkComponent : NetworkApi {

    override val authApiService: AuthApiService
    override val newsApiService: NewsApiService

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ForexBaseUrl baseUrl: String): NetworkComponent
    }
}