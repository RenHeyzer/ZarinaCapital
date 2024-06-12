package com.ren.auth.internal.domain.repositories

import com.ren.auth.internal.domain.entities.reset.ResetNewPasswordModel
import com.ren.auth.internal.domain.entities.reset.ResetPasswordCodeModel
import com.ren.auth.internal.domain.entities.reset.ResetPasswordEmailModel

interface ResetPasswordRepository {
    suspend fun checkEmail(email: String): String

    suspend fun getCode(code: String): String

    suspend fun resetPasswordWithCode(code: String, newPassword: String): String
}