package com.ren.zarinacapital.di

import com.ren.auth.api.presentation.SignInRouter
import com.ren.zarinacapital.navigation.auth.SignInRouterAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RoutersModule {

    @Binds
    fun bindSignInRouterAdapter(adapter: SignInRouterAdapter): SignInRouter
}