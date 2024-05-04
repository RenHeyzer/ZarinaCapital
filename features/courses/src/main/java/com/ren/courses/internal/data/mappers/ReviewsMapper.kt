package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.reviews.Reviews
import com.ren.courses.internal.domain.entities.reviews.ReviewsUser
import com.ren.forexapi.api.models.reviews.ReviewsDTO
import com.ren.forexapi.api.models.reviews.ReviewsUserDTO

import javax.inject.Inject

class ReviewsMapper @Inject constructor(private val userMapper: Mapper<ReviewsUserDTO,ReviewsUser>) :
    Mapper<ReviewsDTO, Reviews> {

    override fun to(model: ReviewsDTO) = with(model) {
        Reviews(
            id = id,
            rating = rating,
            course = course,
            createdAt = createdAt,
            comment = comment,
            user =  userMapper.to(user)
        )

    }

    override fun from(model: Reviews) = with(model) {
        ReviewsDTO(
            id = id,
            rating = rating,
            course = course,
            createdAt = createdAt,
            comment = comment,
            user = userMapper.from(user)
        )
    }
}