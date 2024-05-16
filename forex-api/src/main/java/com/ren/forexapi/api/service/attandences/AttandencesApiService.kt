package com.ren.forexapi.api.service.attandences

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.attandences.AttandencesDTO
import com.ren.forexapi.api.models.news.NewsDTO
import retrofit2.http.GET

private const val END_POINT = "attendances/"

interface AttandencesApiService {

    @GET(END_POINT)
    suspend fun fetchAttandences(): ForexResponse<AttandencesDTO>
}