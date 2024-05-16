package com.ren.menu.internal.domain.entities.schedule

import com.ren.common.Mappable

data class Schedule(
    val date: String = "",
    val id: Int = 0,
    val lessons: List<LessonsItem>?
):Mappable