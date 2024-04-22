package com.ren.auth.api.domain.entities

import com.ren.common.Mappable

data class User(
    val username: String,
    val email: String,
    val phone: String,
    val password: String
) : Mappable