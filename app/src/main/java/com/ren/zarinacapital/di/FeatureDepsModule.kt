package com.ren.zarinacapital.di

import android.content.Context
import com.ren.auth.api.dependencies.AuthDeps
import com.ren.common.AppDispatchers
import com.ren.di.dependencies.FeatureDependencies
import com.ren.di.keys.FeatureDependenciesKey
import com.ren.forexapi.auth.AuthApiService
import com.ren.zarinacapital.auth.di.DaggerAuthDepsComponent
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
        authApiService: AuthApiService,
    ): FeatureDependencies {
        return DaggerAuthDepsComponent.builder()
            .context(context)
            .appDispatchers(appDispatchers)
            .registerApiService(authApiService)
            .build()
    }
}
