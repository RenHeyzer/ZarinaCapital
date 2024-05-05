package com.ren.forexapi.api.models.courses.detail

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class CoursesReviewsItemDTO(
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("course")
    val course: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("user")
    val user: CoursesUserDTO?
):Mappable