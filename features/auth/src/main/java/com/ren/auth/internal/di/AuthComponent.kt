package com.ren.auth.internal.di

import androidx.fragment.app.Fragment
import com.ren.auth.R
import com.ren.auth.api.dependencies.AuthDeps
import com.ren.auth.api.domain.entities.User
import com.ren.auth.api.domain.repositories.AuthRepository
import com.ren.auth.impl.AuthDataRepository
import com.ren.auth.internal.data.mappers.UserMapper
import com.ren.common.Mapper
import com.ren.di.dependencies.findComponentDependencies
import com.ren.di.getComponent
import com.ren.di.scopes.FeatureScope
import com.ren.forexapi.api.models.UserDTO
import dagger.Binds
import dagger.Component
import dagger.Module

@[FeatureScope Component(
    modules = [AuthModule::class],
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

internal val Fragment.authComponent: AuthComponent
    get() = getComponent(R.id.auth_graph) {
        DaggerAuthComponent.builder().deps(findComponentDependencies()).build()
    }

@Module(subcomponents = [SignUpComponent::class])
internal interface AuthModule {

    @Binds
    fun bindUserMapper(mapperImpl: UserMapper): Mapper<UserDTO, User>

    @Binds
    fun bindAuthRepository(repositoryImpl: AuthDataRepository): AuthRepository
}