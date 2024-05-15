package com.ren.mycourses.internal.domain.entities.courses

import com.ren.common.Mappable

data class Courses(
    val image: String = "",
    val price: String = "",
    val rating: Float,
    val id: Int,
    val title: String = ""
):Mappable