package com.ren.auth.internal.data.repositories

import com.ren.auth.internal.domain.entities.User
import com.ren.auth.internal.domain.repositories.AuthRepository
import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.datastore.api.TokenManager
import com.ren.datastore.api.Tokens
import com.ren.forexapi.api.models.LoginParamsDTO
import com.ren.forexapi.api.models.LoginResponse
import com.ren.forexapi.api.models.UserDTO
import com.ren.forexapi.api.models.VerificationCodeDTO
import com.ren.forexapi.api.service.auth.AuthApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AuthDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager,
    @JvmSuppressWildcards private val tokensMapper: Mapper<LoginResponse, Tokens>,
    @JvmSuppressWildcards private val userMapper: Mapper<UserDTO, User>
) : AuthRepository {

    override suspend fun signUp(user: User) {
        withContext(appDispatchers.io) {
            authApiService.signUp(userMapper.from(user))
        }
    }

    override suspend fun confirmEmail(code: Int) {
        withContext(appDispatchers.io) {
            authApiService.confirmEmail(
                VerificationCodeDTO(code)
            )
        }
    }

    override suspend fun login(email: String, password: String) {
        withContext(appDispatchers.io) {
            authApiService.login(
                LoginParamsDTO(email, password)
            ).also { response ->
                tokenManager.saveTokens(tokensMapper.to(response))
            }
        }
    }
}