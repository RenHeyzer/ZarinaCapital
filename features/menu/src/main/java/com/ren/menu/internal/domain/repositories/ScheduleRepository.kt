package com.ren.menu.internal.domain.repositories

import com.ren.common.Either
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.schedule.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun fetchSchedule(): Flow<Either<Throwable, List<Schedule>>>
}