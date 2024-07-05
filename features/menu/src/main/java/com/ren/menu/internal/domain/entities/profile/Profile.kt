package com.ren.menu.internal.domain.entities.profile

import com.ren.common.Mappable
import java.io.Serializable

data class Profile(
    val user: Int,
    val avatar: String? =null,
    val username: String,
    val phone: String,
    val email: String
):Mappable, Serializable
