package com.ren.courses.internal.domain.repositories

import com.ren.common.Either
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.entities.reviews.Reviews
import kotlinx.coroutines.flow.Flow

interface ReviewsRepository {

    fun fetchReviews(): Flow<Either<Throwable, List<Reviews>>>
}