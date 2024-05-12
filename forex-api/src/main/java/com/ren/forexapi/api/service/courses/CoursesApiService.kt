package com.ren.forexapi.api.service.courses

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.models.courses.detail.CoursesDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

private const val END_POINT = "courses/"
private const val END_POINT_DETAIL = "courses/{id}"

interface CoursesApiService {

    @GET(END_POINT)
    suspend fun fetchCourses(): ForexResponse<CoursesDTO>

    @GET(END_POINT_DETAIL)
    suspend fun fetchCoursesDetail(
        @Path("id") id: Int
    ): CoursesDetailDTO
}