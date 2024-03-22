package com.ren.auth.repositories

import com.ren.auth.entities.User

interface AuthRepository {

    suspend fun signUp(user: User)
}