package com.ren.menu.internal.domain.entities.attandences

import com.ren.common.Mappable

data class Attandences(
    val lesson: Int = 0,
    val id: Int = 0,
    val user: UserAttandences,
    val status: Int = 0
):Mappable