package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.detail.CoursesLecturesItem
import com.ren.forexapi.api.models.courses.detail.CoursesLecturesItemDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import javax.inject.Inject

class CoursesLecturesItemMapper @Inject constructor() :
    Mapper<CoursesLecturesItemDTO, CoursesLecturesItem> {

    override fun to(model: CoursesLecturesItemDTO) = with(model) {
        CoursesLecturesItem(
            duration = duration,
            partNumber = partNumber,
            id = id,
            videoFile = videoFile,
            title = title
        )
    }

    override fun from(model: CoursesLecturesItem) = with(model) {
        CoursesLecturesItemDTO(
            duration = duration,
            partNumber = partNumber,
            id = id,
            videoFile = videoFile,
            title = title
        )
    }
}