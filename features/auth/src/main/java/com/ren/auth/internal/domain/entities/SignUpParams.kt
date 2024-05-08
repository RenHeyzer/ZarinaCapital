package com.ren.auth.internal.domain.entities

import com.ren.common.Mappable

internal data class SignUpParams(
    val username: Pair<String, String?>,
    val email: Pair<String, String?>,
    val prefix: String,
    val phone: Pair<String, String?>,
    val password: Pair<String, String?>,
    val confirmPassword: Pair<String, String?>,
) : Mappable