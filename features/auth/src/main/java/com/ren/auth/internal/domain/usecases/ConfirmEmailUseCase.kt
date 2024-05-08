package com.ren.auth.internal.domain.usecases

import com.ren.auth.internal.domain.repositories.AuthRepository
import javax.inject.Inject

internal class ConfirmEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(code: Int) = runCatching { repository.confirmEmail(code) }
}