package com.ren.forexapi.api.models.courses.detail

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class CoursesDetailDTO(
    @SerializedName("image")
    val image: String?,
    @SerializedName("total_duration")
    val totalDuration: Int?,
    @SerializedName("reviews")
    val reviews: List<CoursesReviewsItemDTO>?,
    @SerializedName("lecture_count")
    val lectureCount: Int?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("author")
    val author: CoursesAuthorDTO?,
    @SerializedName("rating")
    val rating: Float?,
    @SerializedName("lectures")
    val lectures: List<CoursesLecturesItemDTO>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
):Mappable