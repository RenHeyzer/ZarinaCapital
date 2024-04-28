package com.ren.auth.internal.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ren.auth.api.dependencies.AuthDeps
import com.ren.auth.api.domain.entities.User
import com.ren.auth.api.domain.repositories.AuthRepository
import com.ren.auth.internal.data.mappers.UserMapper
import com.ren.auth.internal.data.repositories.AuthDataRepository
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.mappers.SignUpParamsMapper
import com.ren.auth.internal.presentation.ui.viewmodels.SignUpViewModel
import com.ren.common.Mapper
import com.ren.di.keys.ViewModelKey
import com.ren.di.scopes.FeatureScope
import com.ren.forexapi.api.models.UserDTO
import com.ren.presentation.base.ViewModelFactory
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@[FeatureScope Component(
    modules = [AuthModule::class],
    dependencies = [AuthDeps::class]
)]
internal interface AuthComponent {

    val viewModelFactory: ViewModelProvider.Factory
}

@Module
internal interface AuthModule {

    @Binds
    fun bindUserMapper(mapperImpl: UserMapper): Mapper<UserDTO, User>

    @Binds
    fun bindSignUpParamsMapper(mapperImpl: SignUpParamsMapper): Mapper<SignUpParams, User>

    @Binds
    fun bindAuthRepository(repositoryImpl: AuthDataRepository): AuthRepository

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(viewModel: SignUpViewModel): ViewModel
}