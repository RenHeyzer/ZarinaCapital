package com.ren.auth.internal.di

import com.ren.auth.entities.SignUpParams
import com.ren.auth.entities.User
import com.ren.auth.mappers.SignUpParamsMapper
import com.ren.common.Mapper
import dagger.Binds
import dagger.Module

@Module
internal interface MappersModule {

    @Binds
    fun bindSignUpParamsMapper(mapperImpl: SignUpParamsMapper): Mapper<SignUpParams, User>
}