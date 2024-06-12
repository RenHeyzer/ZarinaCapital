package com.ren.auth.internal.domain.entities.reset

data class ResetNewPasswordModel(
    val password: String,
    val minLength: Int,
)
