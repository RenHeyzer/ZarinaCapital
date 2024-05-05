package com.ren.courses.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.repositories.CoursesRepository
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.service.courses.CoursesApiService
import javax.inject.Inject

class CoursesDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val coursesMapper: Mapper<CoursesDTO, Courses>,
    private val coursesApiService: CoursesApiService
) : BaseRepository(appDispatchers), CoursesRepository {

    override fun fetchCourses() = doRequest(
        request = {
            coursesApiService.fetchCourses().results
        },
        map = { courses ->
           courses.map(coursesMapper::to)
        }
    )
}