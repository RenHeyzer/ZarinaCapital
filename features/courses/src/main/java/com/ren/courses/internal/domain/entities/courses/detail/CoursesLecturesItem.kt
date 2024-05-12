package com.ren.courses.internal.domain.entities.courses.detail

import com.ren.common.Mappable


data class CoursesLecturesItem(
    val duration: Double?,
    val partNumber: Int?,
    val id: Int?,
    val videoFile: String?,
    val title: String?
):Mappable