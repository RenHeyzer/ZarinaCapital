package com.ren.menu.internal.domain.entities.attandences

import com.ren.common.Mappable


data class UserAttandences(
    val id: Int = 0,
    val totalAttendances: Int = 0,
    val username: String = ""
):Mappable