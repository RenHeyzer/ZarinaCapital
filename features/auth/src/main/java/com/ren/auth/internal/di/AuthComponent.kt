package com.ren.auth.internal.di

import com.ren.di.scopes.FeatureScope
import com.ren.auth.api.dependencies.AuthDeps
import dagger.Component
import dagger.Module

@[FeatureScope Component(
    modules = [AuthModule::class, MappersModule::class],
    dependencies = [AuthDeps::class]
)]
internal interface AuthComponent {

    @Component.Builder
    interface Builder {
        fun deps(deps: AuthDeps): Builder

        fun build(): AuthComponent
    }

    fun signUpComponent(): SignUpComponent.Factory
}

//internal val Fragment.authComponent: AuthComponent by lazy {
//}

@Module(subcomponents = [SignUpComponent::class])
internal class AuthModule
