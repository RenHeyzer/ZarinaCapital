package com.ren.zarinacapital.auth.di

import android.content.Context
import com.ren.auth.api.dependencies.AuthDeps
import com.ren.auth.repositories.AuthRepository
import com.ren.common.AppDispatchers
import com.ren.forexapi.auth.AuthApiService
import com.ren.presentation.utils.ExceptionMessages
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthRepositoriesModule::class, AuthMappersModule::class])
interface AuthDepsComponent : AuthDeps {

    override val exceptionMessages: ExceptionMessages
    override val appDispatchers: AppDispatchers
    override val authApiService: AuthApiService
    override val authRepository: AuthRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        @BindsInstance
        fun appDispatchers(appDispatchers: AppDispatchers): Builder

        @BindsInstance
        fun registerApiService(authApiService: AuthApiService): Builder

        fun build(): AuthDepsComponent
    }
}