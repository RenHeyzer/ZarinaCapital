package com.ren.auth.internal.domain.mappers

import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.entities.User
import com.ren.common.Mapper
import javax.inject.Inject

internal class SignUpParamsMapper @Inject constructor() : Mapper<SignUpParams, User> {
    override fun to(model: SignUpParams) = User(
        username = model.username.first,
        email = model.email.first,
        phone = "${model.prefix}${model.phone.first}",
        password = model.password.first,
    )


    override fun from(model: User) = SignUpParams(
        username = model.username to null,
        email = model.email to null,
        prefix = "+996",
        phone = model.phone to null,
        password = model.password to null,
        confirmPassword = model.password to null
    )
}