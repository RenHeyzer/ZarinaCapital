package com.ren.forexapi.api.service.lessons

import com.ren.forexapi.api.models.lessons.LessonsDTO
import retrofit2.http.GET
import retrofit2.http.Path

private const val END_POINT = "lessons/{id}"

interface LessonsApiService {

    @GET(END_POINT)
    suspend fun fetchLessons(
        @Path("id") id: Int
    ): LessonsDTO
}