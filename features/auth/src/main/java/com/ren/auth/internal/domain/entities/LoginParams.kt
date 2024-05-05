package com.ren.auth.internal.domain.entities

import com.ren.common.Mappable

data class LoginParams(
    val email: String,
    val password: String
): Mappable