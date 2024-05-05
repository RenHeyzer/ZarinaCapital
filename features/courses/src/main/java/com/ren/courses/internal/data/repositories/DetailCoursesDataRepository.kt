package com.ren.courses.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.detail.CoursesDetail
import com.ren.courses.internal.domain.repositories.CoursesDetailRepository
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.courses.detail.CoursesDetailDTO
import com.ren.forexapi.api.service.courses.CoursesApiService
import javax.inject.Inject

class DetailCoursesDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val coursesDetailMapper: Mapper<CoursesDetailDTO, CoursesDetail>,
    private val coursesApiService: CoursesApiService
) : BaseRepository(appDispatchers), CoursesDetailRepository {

    override fun detailCourses(id: Int) = doRequest(
        request = {
            coursesApiService.fetchCoursesDetail(id)
        },
        map = {
            coursesDetailMapper.to(it)
        }
    )
}