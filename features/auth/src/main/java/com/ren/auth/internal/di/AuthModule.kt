package com.ren.auth.internal.di

import com.ren.auth.internal.data.mappers.TokensMapper
import com.ren.auth.internal.data.mappers.UserMapper
import com.ren.auth.internal.data.repositories.AuthDataRepository
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.entities.User
import com.ren.auth.internal.domain.mappers.SignUpParamsMapper
import com.ren.auth.internal.domain.repositories.AuthRepository
import com.ren.common.Mapper
import com.ren.datastore.api.Tokens
import com.ren.forexapi.api.models.auth.LoginResponse
import com.ren.forexapi.api.models.auth.UserDTO
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
    fun bindTokensMapper(mapperImpl: TokensMapper): Mapper<LoginResponse, Tokens>

    @Binds
    @ViewModelScoped
    fun bindAuthRepository(repositoryImpl: AuthDataRepository): AuthRepository
}