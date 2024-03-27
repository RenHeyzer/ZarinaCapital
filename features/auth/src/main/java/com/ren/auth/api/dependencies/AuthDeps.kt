package com.ren.auth.api.dependencies

import com.ren.di.dependencies.FeatureDependencies
import com.ren.auth.repositories.AuthRepository
import com.ren.common.AppDispatchers
import com.ren.forexapi.auth.AuthApiService
import com.ren.presentation.utils.ExceptionMessages

interface AuthDeps : FeatureDependencies {
    val exceptionMessages: ExceptionMessages
    val appDispatchers: AppDispatchers
    val authApiService: AuthApiService
    val authRepository: AuthRepository
}