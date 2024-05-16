package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.courses.detail.CoursesReviewsItemDTO
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.forexapi.api.models.schedule.LessonsItemDTO
import com.ren.forexapi.api.models.schedule.ScheduleDTO
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.schedule.LessonsItem
import com.ren.menu.internal.domain.entities.schedule.Schedule
import javax.inject.Inject

internal class ScheduleMapper @Inject constructor(
    private val lessonsItemMapper: Mapper<LessonsItemDTO, LessonsItem>
) : Mapper<ScheduleDTO, Schedule> {

    override fun to(model: ScheduleDTO) = with(model) {
        Schedule(
            id = id,
            date = date,
            lessons = lessons?.map { first ->
                lessonsItemMapper.to(first)
            },
        )
    }

    override fun from(model: Schedule) = with(model) {
        ScheduleDTO(
            id = id,
            date = date,
            lessons = lessons?.map { first ->
                lessonsItemMapper.from(first)
            }
        )
    }
}