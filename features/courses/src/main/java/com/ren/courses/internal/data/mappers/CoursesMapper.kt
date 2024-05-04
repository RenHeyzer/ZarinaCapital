package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.forexapi.api.models.courses.CoursesDTO
import javax.inject.Inject

class CoursesMapper @Inject constructor() : Mapper<CoursesDTO,Courses > {

    override fun to(model: CoursesDTO) = with(model) {
        Courses(
            image = image,
            price=price,
            rating = rating,
            id = id,
            title = title
        )
    }

    override fun from(model: Courses) = with(model) {
        CoursesDTO(
            image = image,
            price=price,
            rating = rating,
            id = id,
            title = title
        )
    }
}