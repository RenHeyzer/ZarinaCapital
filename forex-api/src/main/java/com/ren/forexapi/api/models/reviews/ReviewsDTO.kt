package com.ren.forexapi.api.models.reviews

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class ReviewsDTO(
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("course")
    val course: Int,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("comment")
    val comment: String = "",
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    val user: ReviewsUserDTO
):Mappable