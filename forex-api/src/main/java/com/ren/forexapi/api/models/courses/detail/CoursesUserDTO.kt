package com.ren.forexapi.api.models.courses.detail

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class CoursesUserDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("username")
    val username: String?
):Mappable