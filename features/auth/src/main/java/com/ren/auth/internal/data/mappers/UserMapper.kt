package com.ren.auth.internal.data.mappers

import com.ren.auth.internal.domain.entities.User
import com.ren.forexapi.api.models.UserDTO
import javax.inject.Inject

internal class UserMapper @Inject constructor() :
    UserMap<UserDTO, User> {

    override fun to(model: UserDTO) = User(
        username = model.username,
        email = model.email,
        phone = model.phone,
        password = model.password
    )

    override fun from(model: User) = UserDTO(
        username = model.username,
        email = model.email,
        phone = model.phone,
        password = model.password
    )
}