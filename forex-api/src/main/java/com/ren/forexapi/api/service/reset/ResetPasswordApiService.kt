package com.ren.forexapi.api.service.reset

import com.ren.forexapi.api.models.reset.ResetNewPasswordDto
import com.ren.forexapi.api.models.reset.ResetPasswordCodeDto
import com.ren.forexapi.api.models.reset.ResetPasswordEmailDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

private const val RESET_PASSWORD_EMAIL = "reset-password-email/"
private const val RESET_PASSWORD_CODE = "reset-password-code/"
private const val RESET_NEW_PASSWORD = "reset-new-password/{code}/"
interface ResetPasswordApiService {

    @POST(RESET_PASSWORD_EMAIL)
    suspend fun resetPasswordEmail(
        @Body email: ResetPasswordEmailDto
    )

    @POST(RESET_PASSWORD_CODE)
    suspend fun resetPasswordCode(
        @Body code: ResetPasswordCodeDto
    )

    @POST(RESET_NEW_PASSWORD)
    suspend fun resetPasswordPassword(
        @Path("code") code: String,
        @Body newPassword: ResetNewPasswordDto
    )
}