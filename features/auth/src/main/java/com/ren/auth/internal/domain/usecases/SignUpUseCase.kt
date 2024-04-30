package com.ren.auth.internal.domain.usecases

import com.ren.auth.internal.domain.entities.SignUpField
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.api.domain.entities.User
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.domain.exceptions.PasswordMismatchException
import com.ren.auth.api.domain.repositories.AuthRepository
import com.ren.common.Mapper
import javax.inject.Inject

internal class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @JvmSuppressWildcards private val signUpParamsMapper: Mapper<SignUpParams, User>
) {

    suspend operator fun invoke(params: SignUpParams) {
        params.apply {
            val emptyFields = mapOf(
                SignUpField.USERNAME to username.first.isEmpty(),
                SignUpField.PHONE to phone.first.isEmpty(),
                SignUpField.EMAIL to email.first.isEmpty(),
                SignUpField.PASSWORD to password.first.isEmpty(),
                SignUpField.CONFIRM_PASSWORD to confirmPassword.first.isEmpty()
            )
            val exceptionMessages = mapOf(
                SignUpField.USERNAME to if (username.first.isEmpty()) username.second else null,
                SignUpField.PHONE to if (phone.first.isEmpty()) phone.second else null,
                SignUpField.EMAIL to if (email.first.isEmpty()) email.second else null,
                SignUpField.PASSWORD to if (password.first.isEmpty()) password.second else null,
                SignUpField.CONFIRM_PASSWORD to if (confirmPassword.first.isEmpty()) confirmPassword.second else null,
            )
            if (emptyFields.containsValue(true)) throw EmptyFieldsException(
                emptyFields,
                exceptionMessages
            )
            if (confirmPassword.first != password.first) throw PasswordMismatchException()
        }.also {
            val user = signUpParamsMapper.to(it)
            authRepository.signUp(user)
        }
    }
}