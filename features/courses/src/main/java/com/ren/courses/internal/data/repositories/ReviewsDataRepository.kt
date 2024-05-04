package com.ren.courses.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.entities.reviews.Reviews
import com.ren.courses.internal.domain.repositories.CoursesRepository
import com.ren.courses.internal.domain.repositories.ReviewsRepository
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.models.reviews.ReviewsDTO
import com.ren.forexapi.api.service.courses.CoursesApiService
import com.ren.forexapi.api.service.raviews.ReviewsApiService
import javax.inject.Inject

class ReviewsDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val reviewsMapper: Mapper<ReviewsDTO, Reviews>,
    private val reviewsApiService: ReviewsApiService
) : BaseRepository(appDispatchers), ReviewsRepository {

    override fun fetchReviews() = doRequest(
        request = {
            reviewsApiService.fetchReviews().results
        },
        map = { news ->
           news.map(reviewsMapper::to)
        }
    )
}