package com.ren.forexapi.api.service.profile

import com.ren.forexapi.api.models.profile.PUTProfileDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

private const val END_POINT = "profile/"

interface ProfileApiService {

    @GET(END_POINT)
    suspend fun fetchProfile(): ProfileDTO

    @PUT(END_POINT)
    suspend fun putProfile(
        @Body profile: PUTProfileDTO
    ): ProfileDTO
}