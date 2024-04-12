package com.ren.zarinacapital.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ren.common.AppDispatchers
import com.ren.di.dependencies.HasFeatureDependencies
import com.ren.forexapi.api.di.NetworkApi
import com.ren.presentation.base.ViewModelFactory
import com.ren.presentation.utils.ExceptionMessages
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@[Singleton Component(
    modules = [
        AppModule::class,
        FeatureDepsModule::class,
    ],
    dependencies = [NetworkApi::class],
)]
interface AppComponent : HasFeatureDependencies {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun networkApi(networkApi: NetworkApi): Builder

        fun build(): AppComponent
    }
}

@Module
interface AppModule {

    companion object {

        @Provides
        @Singleton
        fun provideExceptionMessages(context: Context) = ExceptionMessages(context)

        @Provides
        @Singleton
        fun provideAppDispatchers() = AppDispatchers()
    }
}