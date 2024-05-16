package com.ren.menu.internal.domain.entities.schedule

import com.ren.common.Mappable

data class LessonsItem(
    val startTime: String = "",
    val name: String = "",
    val endTime: String = "",
    val id: Int = 0
):Mappable