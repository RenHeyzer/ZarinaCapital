package com.ren.forexapi.api.service.profile

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import retrofit2.http.GET

private const val END_POINT = "profile/"

interface ProfileApiService {

    @GET(END_POINT)
    suspend fun fetchProfile(): ProfileDTO
}