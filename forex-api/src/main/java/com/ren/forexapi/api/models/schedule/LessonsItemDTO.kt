package com.ren.forexapi.api.models.schedule

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class LessonsItemDTO(
    @SerializedName("start_time")
    val startTime: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("end_time")
    val endTime: String = "",
    @SerializedName("id")
    val id: Int = 0
):Mappable