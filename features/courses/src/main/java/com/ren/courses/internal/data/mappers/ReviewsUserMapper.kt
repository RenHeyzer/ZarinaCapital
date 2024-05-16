package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.reviews.Reviews
import com.ren.courses.internal.domain.entities.reviews.ReviewsUser
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.forexapi.api.models.reviews.ReviewsDTO
import com.ren.forexapi.api.models.reviews.ReviewsUserDTO

import javax.inject.Inject

class ReviewsUserMapper @Inject constructor() : Mapper<ReviewsUserDTO, ReviewsUser> {

    override fun to(model: ReviewsUserDTO) = with(model) {
        ReviewsUser(
            id = id,
            avatar = avatar,
            username = username
        )

    }

    override fun from(model: ReviewsUser) = with(model) {
        ReviewsUserDTO(
            id = id,
            avatar = avatar,
            username = username
        )
    }
}