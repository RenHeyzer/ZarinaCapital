package com.ren.zarinacapital.di

import android.content.Context
import com.ren.menu.api.dependencies.NewsDeps
import com.ren.common.AppDispatchers
import com.ren.di.dependencies.FeatureDependencies
import com.ren.di.keys.FeatureDependenciesKey
import com.ren.forexapi.api.di.NetworkApi
import com.ren.zarinacapital.di.auth.DaggerAuthDepsComponent
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object FeatureDepsModule {

    @Provides
    @IntoMap
    @FeatureDependenciesKey(NewsDeps::class)
    fun provideAuthDeps(
        context: Context,
        appDispatchers: AppDispatchers,
        networkApi: NetworkApi,
    ): FeatureDependencies {
        return DaggerAuthDepsComponent.builder()
            .context(context)
            .appDispatchers(appDispatchers)
            .authApiService(networkApi.authApiService)
            .build()
    }

    @Provides
    @IntoMap
    @FeatureDependenciesKey(NewsDeps::class)
    fun provideNewsDeps(
        context: Context,
        appDispatchers: AppDispatchers,
        networkApi: NetworkApi,
    ): FeatureDependencies {
        return DaggerNewsDepsComponent.builder()
            .context(context)
            .appDispatchers(appDispatchers)
            .newsApiService(networkApi.newsApiService)
            .build()
    }
}
