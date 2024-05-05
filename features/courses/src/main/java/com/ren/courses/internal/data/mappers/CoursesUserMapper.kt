package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.entities.courses.detail.CoursesAuthor
import com.ren.courses.internal.domain.entities.courses.detail.CoursesUser
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.models.courses.detail.CoursesAuthorDTO
import com.ren.forexapi.api.models.courses.detail.CoursesUserDTO
import javax.inject.Inject

class CoursesUserMapper @Inject constructor() : Mapper<CoursesUserDTO, CoursesUser> {

    override fun to(model: CoursesUserDTO) = with(model) {
        CoursesUser(
            id = id,
            avatar = avatar,
            username = username
        )
    }

    override fun from(model: CoursesUser) = with(model) {
        CoursesUserDTO(
            id = id,
            avatar = avatar,
            username = username
        )
    }
}