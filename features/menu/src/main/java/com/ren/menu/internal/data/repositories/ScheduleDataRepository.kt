package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.schedule.ScheduleDTO
import com.ren.forexapi.api.service.news.NewsApiService
import com.ren.forexapi.api.service.schedule.ScheduleApiService
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.schedule.Schedule
import com.ren.menu.internal.domain.repositories.NewsRepository
import com.ren.menu.internal.domain.repositories.ScheduleRepository
import javax.inject.Inject

internal class ScheduleDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val scheduleMapper: Mapper<ScheduleDTO, Schedule>,
    private val scheduleApiService: ScheduleApiService
) : BaseRepository(appDispatchers), ScheduleRepository {

    override fun fetchSchedule() = doRequest(
        request = {
            scheduleApiService.fetchSchedule().results
        },
        map = { news ->
            news.map(scheduleMapper::to)
        }
    )
}