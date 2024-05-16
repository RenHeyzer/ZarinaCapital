package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.forexapi.api.models.schedule.LessonsItemDTO
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.schedule.LessonsItem
import javax.inject.Inject

internal class LessonsItemMapper @Inject constructor() : Mapper<LessonsItemDTO, LessonsItem> {

    override fun to(model: LessonsItemDTO) = with(model) {
        LessonsItem(
            id = id,
            startTime = startTime,
            endTime = endTime,
            name = name
        )
    }

    override fun from(model: LessonsItem) = with(model) {
        LessonsItemDTO(
            id = id,
            startTime = startTime,
            endTime = endTime,
            name = name
        )
    }
}