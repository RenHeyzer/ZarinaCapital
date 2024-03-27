package com.ren.auth.impl

import com.ren.auth.entities.User
import com.ren.auth.repositories.AuthRepository
import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.forexapi.auth.AuthApiService
import com.ren.forexapi.models.UserDTO
import com.ren.forexapi.models.VerificationCodeDTO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val authApiService: AuthApiService,
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
}