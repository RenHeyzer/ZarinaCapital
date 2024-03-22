package com.ren.auth.entities

import com.ren.auth.exceptions.EmptyFieldException
import com.ren.common.Mappable

data class SignUpParams(
    val username: String,
    val email: String,
    val phone: String,
    val password: String,
    val confirmPassword: String,
) : Mappable {

    fun throwExceptionIfEmpty(param: String, field: SignUpField) {
        if (param.isEmpty()) throw EmptyFieldException(field)
    }
}
