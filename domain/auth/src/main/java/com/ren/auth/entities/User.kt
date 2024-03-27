package com.ren.auth.entities

import com.ren.common.Mappable

data class User(
    val username: String,
    val email: String,
    val phone: String,
    val password: String
) : Mappable