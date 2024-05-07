package com.ren.forexapi.api.auth

import com.ren.forexapi.api.models.LoginParamsDTO
import com.ren.forexapi.api.models.LoginResponse
import com.ren.forexapi.api.models.UserDTO
import com.ren.forexapi.api.models.VerificationCodeDTO
import retrofit2.http.Body
import retrofit2.http.POST

private const val REGISTER_END_POINT = "register/"
private const val CONFIRM_END_POINT = "confirm/"
private const val LOGIN_END_POINT = "login/"

interface AuthApiService {

    @POST(REGISTER_END_POINT)
    suspend fun signUp(
        @Body user: UserDTO
    ): UserDTO

    @POST(CONFIRM_END_POINT)
    suspend fun confirmEmail(
        @Body verificationCodeDTO: VerificationCodeDTO
    )

    @POST(LOGIN_END_POINT)
    suspend fun login(
        @Body loginParamsDTO: LoginParamsDTO
    ): LoginResponse
}