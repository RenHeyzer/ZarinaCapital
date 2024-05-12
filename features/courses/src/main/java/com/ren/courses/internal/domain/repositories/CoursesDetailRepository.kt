package com.ren.courses.internal.domain.repositories

import com.ren.common.Either
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.entities.courses.detail.CoursesDetail
import kotlinx.coroutines.flow.Flow

interface CoursesDetailRepository {

    fun detailCourses(id:Int): Flow<Either<Throwable, CoursesDetail>>
}