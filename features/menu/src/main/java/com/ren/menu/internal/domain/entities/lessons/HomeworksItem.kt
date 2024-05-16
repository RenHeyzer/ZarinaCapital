package com.ren.menu.internal.domain.entities.lessons

import com.ren.common.Mappable


data class HomeworksItem(
    val homework: String = "",
    val name: String = "",
    val description: String = "",
    val id: Int = 0,
    val deadline: String = ""
):Mappable