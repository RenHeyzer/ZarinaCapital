package com.ren.auth.internal.domain.usecases

import com.ren.auth.internal.domain.entities.AuthFields
import com.ren.auth.internal.domain.entities.AuthFields.LoginField
import com.ren.auth.internal.domain.entities.LoginParams
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.domain.repositories.AuthRepository
import javax.inject.Inject

internal class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(params: LoginParams) = runCatching {
        params.apply {
            val emptyFields = mapOf<AuthFields, Boolean>(
                LoginField.EMAIL to email.first.isEmpty(),
                LoginField.PASSWORD to password.first.isEmpty(),
            )
            val exceptionMessages = mapOf<AuthFields, String?>(
                LoginField.EMAIL to if (email.first.isEmpty()) email.second else null,
                LoginField.PASSWORD to if (password.first.isEmpty()) password.second else null,
            )
            if (emptyFields.containsValue(true)) throw EmptyFieldsException(
                emptyFields,
                exceptionMessages
            )
        }.also {
            authRepository.login(it.email.first, it.password.first)
        }
    }
}