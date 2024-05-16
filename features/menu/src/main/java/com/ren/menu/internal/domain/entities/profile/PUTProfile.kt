package com.ren.menu.internal.domain.entities.profile

import com.ren.common.Mappable

data class PUTProfile(
    val username: String,
    val phone: String,
    val email: String
):Mappable
