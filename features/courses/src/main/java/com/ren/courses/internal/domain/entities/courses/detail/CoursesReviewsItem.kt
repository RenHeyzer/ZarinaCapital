package com.ren.courses.internal.domain.entities.courses.detail

import com.ren.common.Mappable

data class CoursesReviewsItem(
    val rating: Int?,
    val course: Int?,
    val createdAt: String?,
    val comment: String?,
    val id: Int?,
    val user: CoursesUser?
):Mappable