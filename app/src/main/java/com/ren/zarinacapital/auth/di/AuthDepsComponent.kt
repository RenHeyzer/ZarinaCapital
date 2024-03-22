package com.ren.zarinacapital.auth.di

import android.content.Context
import com.ren.auth.api.dependencies.AuthDeps
import com.ren.auth.api.dependencies.AuthDepsProvider
import com.ren.auth.repositories.AuthRepository
import com.ren.presentation.utils.ExceptionMessages
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AuthRepositoriesModule::class, AuthMappersModule::class])
interface AuthDepsComponent : AuthDeps {

    override val exceptionMessages: ExceptionMessages
    override val authRepository: AuthRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AuthDepsComponent
    }
}