package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.entities.courses.detail.CoursesAuthor
import com.ren.courses.internal.domain.entities.courses.detail.CoursesReviewsItem
import com.ren.courses.internal.domain.entities.courses.detail.CoursesUser
import com.ren.courses.internal.domain.entities.reviews.ReviewsUser
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.models.courses.detail.CoursesAuthorDTO
import com.ren.forexapi.api.models.courses.detail.CoursesReviewsItemDTO
import com.ren.forexapi.api.models.courses.detail.CoursesUserDTO
import com.ren.forexapi.api.models.reviews.ReviewsUserDTO
import javax.inject.Inject

class CoursesReviewsItemMapper @Inject constructor(private val userMapper: Mapper<CoursesUserDTO, CoursesUser>) :
    Mapper<CoursesReviewsItemDTO, CoursesReviewsItem> {

    override fun to(model: CoursesReviewsItemDTO) = with(model) {
        CoursesReviewsItem(
            rating = rating,
            course = course,
            createdAt = createdAt,
            comment = comment,
            id = id,
            user?.let {
                userMapper.to(it)
            },
        )
    }

    override fun from(model: CoursesReviewsItem) = with(model) {
        CoursesReviewsItemDTO(
            rating = rating,
            course = course,
            createdAt = createdAt,
            comment = comment,
            id = id,
            user?.let {
                userMapper.from(it)
            },
        )
    }
}