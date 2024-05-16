package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.lessons.HomeworksItemDTO
import com.ren.menu.internal.domain.entities.lessons.HomeworksItem
import javax.inject.Inject

internal class HomeWorksMapper @Inject constructor() : Mapper<HomeworksItemDTO, HomeworksItem> {

    override fun to(model: HomeworksItemDTO) = with(model) {
        HomeworksItem(
            id = id,
            homework = homework,
            description = description,
            name = name,
            deadline = deadline
        )
    }

    override fun from(model: HomeworksItem) = with(model) {
        HomeworksItemDTO(
            id = id,
            homework = homework,
            description = description,
            name = name,
            deadline = deadline
        )
    }
}