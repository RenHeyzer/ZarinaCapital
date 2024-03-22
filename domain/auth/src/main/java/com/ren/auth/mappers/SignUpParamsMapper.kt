package com.ren.auth.mappers

import com.ren.auth.entities.SignUpParams
import com.ren.auth.entities.User
import com.ren.common.Mapper
import javax.inject.Inject

class SignUpParamsMapper @Inject constructor() : Mapper<SignUpParams, User> {
    override fun to(model: SignUpParams) = User(
        username = model.username,
        email = model.email,
        phone = model.phone,
        password = model.password,
    )


    override fun from(model: User) = SignUpParams(
        username = model.username,
        email = model.email,
        phone = model.phone,
        password = model.password,
        confirmPassword = model.password
    )
}