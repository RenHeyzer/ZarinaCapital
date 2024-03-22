package com.ren.zarinacapital.di

import android.content.Context
import com.geeks.di.dependencies.HasFeatureDependencies
import com.ren.presentation.utils.ExceptionMessages
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@[Singleton Component(
    modules = [
        AppModule::class,
        FeatureDepsModule::class,
        ApiModule::class
    ],
)]
interface AppComponent : HasFeatureDependencies {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context): Builder

        @BindsInstance
        fun baseUrl(@ForexBaseUrl baseUrl: String): Builder

        fun build(): AppComponent
    }

//    fun authAdapterComponent(): AuthAdapterComponent.Factory
}

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideExceptionMessages(context: Context) = ExceptionMessages(context)
}