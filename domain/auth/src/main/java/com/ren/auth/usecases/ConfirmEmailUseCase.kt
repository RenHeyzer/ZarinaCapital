package com.ren.auth.usecases

import com.ren.auth.repositories.AuthRepository
import javax.inject.Inject

class ConfirmEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(code: Int) {
        repository
    }
}