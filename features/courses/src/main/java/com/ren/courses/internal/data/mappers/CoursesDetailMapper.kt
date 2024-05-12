package com.ren.courses.internal.data.mappers

import com.ren.common.Mapper
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.entities.courses.detail.CoursesAuthor
import com.ren.courses.internal.domain.entities.courses.detail.CoursesDetail
import com.ren.courses.internal.domain.entities.courses.detail.CoursesLecturesItem
import com.ren.courses.internal.domain.entities.courses.detail.CoursesReviewsItem
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.forexapi.api.models.courses.detail.CoursesAuthorDTO
import com.ren.forexapi.api.models.courses.detail.CoursesDetailDTO
import com.ren.forexapi.api.models.courses.detail.CoursesLecturesItemDTO
import com.ren.forexapi.api.models.courses.detail.CoursesReviewsItemDTO
import javax.inject.Inject

class CoursesDetailMapper @Inject constructor(
    private val reviewsMapper: Mapper<CoursesReviewsItemDTO, CoursesReviewsItem>,
    private val authorMapper: Mapper<CoursesAuthorDTO, CoursesAuthor>,
    private val lecturesMapper: Mapper<CoursesLecturesItemDTO, CoursesLecturesItem>
) : Mapper<CoursesDetailDTO, CoursesDetail> {

    override fun to(model: CoursesDetailDTO) = with(model) {
        CoursesDetail(
            image = image,
            totalDuration = totalDuration,
            reviews = reviews?.map { reviewsFirst ->
                reviewsMapper.to(reviewsFirst)
            },
            lectureCount = lectureCount,
            price = price,
            author?.let {
                authorMapper.to(it)
            },
            rating = rating,
            lectures = lectures?.map { lectureFirst ->
                lecturesMapper.to(lectureFirst)
            },
            id = id,
            title = title
        )
    }

    override fun from(model: CoursesDetail) = with(model) {
        CoursesDetailDTO(
            image = image,
            totalDuration = totalDuration,
            reviews = reviews?.map { reviewsSecond ->
                reviewsMapper.from(reviewsSecond)
            },
            lectureCount = lectureCount,
            price = price,
            author?.let {
                authorMapper.from(it)
            },
            rating = rating,
            lectures = lectures?.map { lectureSecond ->
                lecturesMapper.from(lectureSecond)
            },
            id = id,
            title = title
        )
    }
}