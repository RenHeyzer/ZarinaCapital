package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.entities.courses.detail.CoursesAuthor
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.models.courses.detail.CoursesAuthorDTO
import javax.inject.Inject

class CoursesAuthorMapper @Inject constructor() : Mapper<CoursesAuthorDTO, CoursesAuthor> {

    override fun to(model: CoursesAuthorDTO) = with(model) {
        CoursesAuthor(
            id = id,
            avatar = avatar,
            username = username
        )
    }

    override fun from(model: CoursesAuthor) = with(model) {
        CoursesAuthorDTO(
            id = id,
            avatar = avatar,
            username = username
        )
    }
}