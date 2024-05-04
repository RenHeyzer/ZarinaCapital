package com.ren.forexapi.api.service.courses

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.courses.CoursesDTO
import retrofit2.http.GET

private const val END_POINT = "courses/"

interface CoursesApiService {

    @GET(END_POINT)
    suspend fun fetchCourses(): ForexResponse<CoursesDTO>
}