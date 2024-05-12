package com.ren.forexapi.api.models.courses

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class CoursesDTO(
    @SerializedName("image")
    val image: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String = ""
):Mappable