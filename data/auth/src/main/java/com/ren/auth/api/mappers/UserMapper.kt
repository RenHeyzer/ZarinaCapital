package com.ren.auth.api.mappers

import com.ren.auth.entities.User
import com.ren.forexapi.models.UserDTO
import javax.inject.Inject

class UserMapper @Inject constructor() : UserMap<UserDTO, User> {

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