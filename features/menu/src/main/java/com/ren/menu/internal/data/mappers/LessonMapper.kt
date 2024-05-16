package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.lessons.HomeworksItemDTO
import com.ren.forexapi.api.models.lessons.LessonsDTO
import com.ren.forexapi.api.models.schedule.LessonsItemDTO
import com.ren.menu.internal.domain.entities.lessons.HomeworksItem
import com.ren.menu.internal.domain.entities.lessons.Lessons
import com.ren.menu.internal.domain.entities.schedule.LessonsItem
import javax.inject.Inject

internal class LessonMapper @Inject constructor(
    private val homeworksItemMapper: Mapper<HomeworksItemDTO, HomeworksItem>
) : Mapper<LessonsDTO, Lessons> {

    override fun to(model: LessonsDTO) = with(model) {
        Lessons(
            id = id,
            startTime = startTime,
            name = name,
            endTime = endTime,
            homeworks = homeworks?.map {
                homeworksItemMapper.to(it)
            }
        )
    }

    override fun from(model: Lessons) = with(model) {
        LessonsDTO(
            id = id,
            startTime = startTime,
            name = name,
            endTime = endTime,
            homeworks = homeworks?.map {
                homeworksItemMapper.from(it)
            }
        )
    }
}