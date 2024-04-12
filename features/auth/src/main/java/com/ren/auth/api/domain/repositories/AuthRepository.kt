package com.ren.auth.api.domain.repositories

import com.ren.auth.api.domain.entities.User

interface AuthRepository {

    suspend fun signUp(user: User)

    suspend fun confirmEmail(code: Int)
}