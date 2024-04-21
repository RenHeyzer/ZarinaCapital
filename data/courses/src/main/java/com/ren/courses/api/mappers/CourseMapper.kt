package com.ren.courses.api.mappers

import com.ren.common.Mapper
import com.ren.courses.entities.Course
import com.ren.forexapi.courses.models.CourseDTO
import javax.inject.Inject

class CourseMapper @Inject constructor() : Mapper<CourseDTO, Course> {

    override fun to(model: CourseDTO) = Course(
        id = model.id,
        title = model.title,
        image = model.image,
        price = model.price,
        rating = model.rating
    )

    override fun from(model: Course) = CourseDTO(
        id = model.id,
        title = model.title,
        image = model.image,
        price = model.price,
        rating = model.rating
    )
}