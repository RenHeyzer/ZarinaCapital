package com.ren.auth.impl

import com.ren.auth.entities.User
import com.ren.auth.repositories.AuthRepository
import javax.inject.Inject

class AuthDataRepository @Inject constructor() : AuthRepository {

    override suspend fun signUp(user: User) {

    }
}