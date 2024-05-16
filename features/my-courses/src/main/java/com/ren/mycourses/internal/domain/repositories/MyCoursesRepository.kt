package com.ren.mycourses.internal.domain.repositories

import com.ren.common.Either
import com.ren.mycourses.internal.domain.entities.courses.Courses
import kotlinx.coroutines.flow.Flow

interface MyCoursesRepository {

    fun fetchCourses(): Flow<Either<Throwable, List<Courses>>>
}