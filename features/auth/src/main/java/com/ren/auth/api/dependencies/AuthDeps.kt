package com.ren.auth.api.dependencies

import com.geeks.di.dependencies.FeatureDependencies
import com.ren.auth.repositories.AuthRepository
import com.ren.presentation.utils.ExceptionMessages

interface AuthDeps : FeatureDependencies {
    val exceptionMessages: ExceptionMessages
    val authRepository: AuthRepository
}

interface AuthDepsProvider {
    val deps: AuthDeps
}