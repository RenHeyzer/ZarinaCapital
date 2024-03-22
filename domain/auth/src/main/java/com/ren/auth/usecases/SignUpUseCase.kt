package com.ren.auth.usecases

import com.ren.auth.entities.SignUpField
import com.ren.auth.entities.SignUpParams
import com.ren.auth.entities.User
import com.ren.auth.exceptions.PasswordMismatchException
import com.ren.auth.repositories.AuthRepository
import com.ren.common.Mapper
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @JvmSuppressWildcards private val signUpParamsMapper: Mapper<SignUpParams, User>
) {

    suspend operator fun invoke(params: SignUpParams) {
        params.apply {
            throwExceptionIfEmpty(username, SignUpField.USERNAME)
            throwExceptionIfEmpty(phone, SignUpField.PHONE)
            throwExceptionIfEmpty(email, SignUpField.EMAIL)
            throwExceptionIfEmpty(password, SignUpField.PASSWORD)
            throwExceptionIfEmpty(confirmPassword, SignUpField.CONFIRM_PASSWORD)
            if (password != confirmPassword) throw PasswordMismatchException()
        }.also {
            val user = signUpParamsMapper.to(it)
            authRepository.signUp(user)
        }
    }
}