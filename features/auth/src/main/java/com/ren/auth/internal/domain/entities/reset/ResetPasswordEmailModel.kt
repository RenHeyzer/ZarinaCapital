package com.ren.auth.internal.domain.entities.reset

data class ResetPasswordEmailModel(
    val email: String,
    val minLength: Int,
)