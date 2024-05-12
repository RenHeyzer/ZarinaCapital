package com.ren.forexapi.api.models.courses.detail

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class CoursesLecturesItemDTO(
    @SerializedName("duration")
    val duration: Double?,
    @SerializedName("part_number")
    val partNumber: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("video_file")
    val videoFile: String?,
    @SerializedName("title")
    val title: String?
):Mappable