package com.ren.courses.internal.domain.entities.reviews

import com.ren.common.Mappable


data class ReviewsUser(
    val id: Int,
    val avatar: String? = null,
    val username: String = ""
) : Mappable