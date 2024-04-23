package com.ren.auth.internal.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ren.auth.api.domain.entities.User
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.mappers.SignUpParamsMapper
import com.ren.di.keys.ViewModelKey
import com.ren.di.scopes.ScreenScope
import com.ren.auth.internal.presentation.ui.viewmodels.SignUpViewModel
import com.ren.common.Mapper
import com.ren.presentation.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@[ScreenScope Subcomponent(modules = [SingUpModule::class])]
internal interface SignUpComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): SignUpComponent
    }
}

@Module
internal interface SingUpModule {

    @Binds
    fun bindSignUpParamsMapper(mapperImpl: SignUpParamsMapper): Mapper<SignUpParams, User>

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(viewModel: SignUpViewModel): ViewModel
}
