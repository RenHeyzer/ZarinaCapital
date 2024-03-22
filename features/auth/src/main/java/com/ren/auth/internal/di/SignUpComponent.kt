package com.ren.auth.internal.di

import androidx.lifecycle.ViewModel
import com.geeks.di.keys.ViewModelKey
import com.geeks.di.scopes.ScreenScope
import com.ren.auth.internal.presentation.ui.viewmodels.SignUpViewModel
import com.ren.presentation.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@[ScreenScope Subcomponent(modules = [SingUpModule::class])]
internal interface SignUpComponent {

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(): SignUpComponent
    }
}

@Module
internal interface SingUpModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(viewModel: SignUpViewModel): ViewModel
}
