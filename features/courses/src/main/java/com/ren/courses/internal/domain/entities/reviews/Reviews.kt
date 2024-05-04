package com.ren.courses.internal.domain.entities.reviews

import com.ren.common.Mappable

data class Reviews(
    val rating: Float,
    val course: Int,
    val createdAt: String = "",
    val comment: String = "",
    val id: Int,
    val user: ReviewsUser
) : Mappable