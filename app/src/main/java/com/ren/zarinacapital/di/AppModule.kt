package com.ren.zarinacapital.di

import android.content.Context
import com.ren.common.AppDispatchers
import com.ren.forexapi.api.di.ForexBaseUrl
import com.ren.presentation.utils.ExceptionMessages
import com.ren.zarinacapital.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDispatchers() = AppDispatchers()

    @Provides
    @Singleton
    fun provideExceptionMessages(@ApplicationContext context: Context) = ExceptionMessages(context)

    @ForexBaseUrl
    @Provides
    @Singleton
    fun provideForexBaseUrl() = BuildConfig.FOREX_BASE_URL
}