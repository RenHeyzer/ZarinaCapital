package com.ren.zarinacapital.di

import android.content.Context
import com.ren.auth.api.dependencies.AuthDeps
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
    @FeatureDependenciesKey(AuthDeps::class)
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
}
