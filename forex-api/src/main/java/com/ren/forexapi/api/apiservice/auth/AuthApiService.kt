package com.ren.forexapi.api.apiservice.auth

import com.ren.forexapi.api.models.UserDTO
import com.ren.forexapi.api.models.VerificationCodeDTO
import retrofit2.http.Body
import retrofit2.http.POST

private const val REGISTER_END_POINT = "register/"
private const val CONFIRM_END_POINT = "confirm/"

interface AuthApiService {

    @POST(REGISTER_END_POINT)
    suspend fun signUp(
        @Body user: UserDTO
    ): UserDTO

    @POST(CONFIRM_END_POINT)
    suspend fun confirmEmail(
        @Body verificationCodeDTO: VerificationCodeDTO
    )
}