package com.ren.auth.internal.di

import com.ren.auth.api.dependencies.AuthDeps
import com.ren.auth.api.domain.entities.User
import com.ren.auth.api.domain.repositories.AuthRepository
import com.ren.auth.internal.data.mappers.UserMapper
import com.ren.auth.internal.data.repositories.AuthDataRepository
import com.ren.common.Mapper
import com.ren.di.scopes.FeatureScope
import com.ren.forexapi.api.models.UserDTO
import dagger.Binds
import dagger.Component
import dagger.Module

@[FeatureScope Component(
    modules = [AuthModule::class],
    dependencies = [AuthDeps::class]
)]
internal interface AuthComponent

@Module
internal interface AuthModule {

    @Binds
    fun bindUserMapper(mapperImpl: UserMapper): Mapper<UserDTO, User>

    @Binds
    fun bindAuthRepository(repositoryImpl: AuthDataRepository): AuthRepository
}