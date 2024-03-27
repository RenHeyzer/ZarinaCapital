package com.ren.auth.entities

import com.ren.common.Mappable

data class SignUpParams(
    val username: Pair<String, String?>,
    val email: Pair<String, String?>,
    val phone: Pair<String, String?>,
    val password: Pair<String, String?>,
    val confirmPassword: Pair<String, String?>,
) : Mappable