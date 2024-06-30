package com.ren.auth.internal.domain.entities.reset

data class ResetPasswordCodeModel(
    val code: String,
    val minLength: Int,
)
