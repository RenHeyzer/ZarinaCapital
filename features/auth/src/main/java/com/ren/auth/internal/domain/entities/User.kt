package com.ren.auth.internal.domain.entities

import com.ren.common.Mappable

internal data class User(
    val username: String,
    val email: String,
    val phone: String,
    val password: String
) : Mappable