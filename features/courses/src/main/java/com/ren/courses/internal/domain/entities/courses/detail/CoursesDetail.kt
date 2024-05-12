package com.ren.courses.internal.domain.entities.courses.detail

import com.ren.common.Mappable


data class CoursesDetail(
    val image: String?,
    val totalDuration: Int? ,
    val reviews: List<CoursesReviewsItem>?,
    val lectureCount: Int?,
    val price: String?,
    val author: CoursesAuthor?,
    val rating: Float?,
    val lectures: List<CoursesLecturesItem>?,
    val id: Int?,
    val title: String?
):Mappable