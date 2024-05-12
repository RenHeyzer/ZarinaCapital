package com.ren.forexapi.api.models.reviews

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class ReviewsUserDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("username")
    val username: String = ""
):Mappable