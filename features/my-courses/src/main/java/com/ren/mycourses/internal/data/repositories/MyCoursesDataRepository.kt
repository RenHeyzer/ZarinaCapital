package com.ren.mycourses.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.mycourses.internal.domain.entities.courses.Courses
import com.ren.mycourses.internal.domain.repositories.MyCoursesRepository
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.service.courses.CoursesApiService
import javax.inject.Inject

class MyCoursesDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val coursesMapper: Mapper<CoursesDTO, Courses>,
    private val coursesApiService: CoursesApiService
) : BaseRepository(appDispatchers), MyCoursesRepository {

    override fun fetchCourses() = doRequest(
        request = {
            coursesApiService.fetchCourses().results
        },
        map = { courses ->
           courses.map(coursesMapper::to)
        }
    )
}