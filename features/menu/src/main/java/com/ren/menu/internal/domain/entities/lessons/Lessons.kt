package com.ren.menu.internal.domain.entities.lessons

import com.ren.common.Mappable


data class Lessons(
    val startTime: String = "",
    val homeworks: List<HomeworksItem>?,
    val name: String = "",
    val endTime: String = "",
    val id: Int = 0
):Mappable