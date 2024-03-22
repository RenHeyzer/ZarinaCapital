package com.ren.zarinacapital.auth.di

import com.ren.auth.api.mappers.UserMapper
import com.ren.auth.entities.SignUpParams
import com.ren.auth.entities.User
import com.ren.auth.mappers.SignUpParamsMapper
import com.ren.common.Mapper
import com.ren.forexapi.models.UserDTO
import dagger.Binds
import dagger.Module

@Module
interface AuthMappersModule {

    @Binds
    fun bindSignUpParamsMapper(mapperImpl: SignUpParamsMapper): Mapper<SignUpParams, User>

    @Binds
    fun bindUserMapper(mapperImpl: UserMapper): Mapper<UserDTO, User>
}