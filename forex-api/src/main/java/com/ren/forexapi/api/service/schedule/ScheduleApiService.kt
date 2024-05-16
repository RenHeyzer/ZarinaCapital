package com.ren.forexapi.api.service.schedule

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.auth.UserDTO
import com.ren.forexapi.api.models.schedule.ScheduleDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val END_POINT = "schedules/"

interface ScheduleApiService {

    @GET(END_POINT)
    suspend fun fetchSchedule(): ForexResponse<ScheduleDTO>
}