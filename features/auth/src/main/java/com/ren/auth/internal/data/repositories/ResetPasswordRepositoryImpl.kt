package com.ren.auth.internal.data.repositories

import com.ren.auth.internal.domain.repositories.ResetPasswordRepository
import com.ren.forexapi.api.models.reset.ResetNewPasswordDto
import com.ren.forexapi.api.models.reset.ResetPasswordCodeDto
import com.ren.forexapi.api.models.reset.ResetPasswordEmailDto
import com.ren.forexapi.api.service.reset.ResetPasswordApiService
import javax.inject.Inject

class ResetPasswordRepositoryImpl @Inject constructor(
    private val resetPasswordApiService: ResetPasswordApiService
) : ResetPasswordRepository {
    override suspend fun checkEmail(email: String): String {
        return resetPasswordApiService.resetPasswordEmail(ResetPasswordEmailDto(email)).toString()
    }

    override suspend fun getCode(code: String): String {
        return resetPasswordApiService.resetPasswordCode(ResetPasswordCodeDto(code)).toString()
    }

    override suspend fun resetPasswordWithCode(code: String, newPassword: String): String {
        return resetPasswordApiService.resetPasswordPassword(
            code,
            ResetNewPasswordDto(newPassword)
        ).toString()
    }
}