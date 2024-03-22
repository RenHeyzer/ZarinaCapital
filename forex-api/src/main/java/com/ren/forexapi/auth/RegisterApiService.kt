package com.ren.forexapi.auth

import com.ren.forexapi.models.UserDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val END_POINT = "register"

interface RegisterApiService {

    @POST(END_POINT)
    suspend fun signUp(
        @Body user: UserDTO
    ): UserDTO
}