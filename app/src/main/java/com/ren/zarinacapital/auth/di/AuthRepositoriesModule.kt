package com.ren.zarinacapital.auth.di

import com.ren.auth.impl.AuthDataRepository
import com.ren.auth.repositories.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface AuthRepositoriesModule {

    @Binds
    fun bindAuthRepository(repositoryImpl: AuthDataRepository): AuthRepository
}