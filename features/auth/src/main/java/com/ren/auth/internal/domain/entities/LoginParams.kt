package com.ren.auth.internal.domain.entities

import com.ren.common.Mappable

data class LoginParams(
    val email: Pair<String, String?>,
    val password: Pair<String, String?>
): Mappable