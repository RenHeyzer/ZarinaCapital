package com.ren.auth.internal.domain.repositories

import com.ren.auth.internal.domain.entities.User

internal interface AuthRepository {

    suspend fun signUp(user: User)

    suspend fun confirmEmail(code: Int)
}