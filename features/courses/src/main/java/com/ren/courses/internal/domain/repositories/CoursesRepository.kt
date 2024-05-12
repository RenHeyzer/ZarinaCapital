package com.ren.courses.internal.domain.repositories

import com.ren.common.Either
import com.ren.courses.internal.domain.entities.courses.Courses
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {

    fun fetchCourses(): Flow<Either<Throwable, List<Courses>>>
}