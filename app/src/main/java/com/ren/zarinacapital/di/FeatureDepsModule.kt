package com.ren.zarinacapital.di

import android.content.Context
import com.geeks.di.dependencies.FeatureDependencies
import com.geeks.di.keys.FeatureDependenciesKey
import com.ren.auth.api.dependencies.AuthDeps
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
    ): FeatureDependencies {
        return DaggerAuthDepsComponent.builder().context(context).build()
    }
}
