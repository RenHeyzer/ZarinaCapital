package com.ren.auth.internal.di

import com.ren.auth.internal.domain.entities.User
import com.ren.auth.internal.domain.repositories.AuthRepository
import com.ren.auth.internal.data.mappers.UserMapper
import com.ren.auth.internal.data.repositories.AuthDataRepository
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.mappers.SignUpParamsMapper
import com.ren.common.Mapper
import com.ren.forexapi.api.models.UserDTO
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface AuthModule {

    @Binds
    @ViewModelScoped
    fun bindUserMapper(mapperImpl: UserMapper): Mapper<UserDTO, User>

    @Binds
    @ViewModelScoped
    fun bindSignUpParamsMapper(mapperImpl: SignUpParamsMapper): Mapper<SignUpParams, User>

    @Binds
    @ViewModelScoped
    fun bindAuthRepository(repositoryImpl: AuthDataRepository): AuthRepository
}