package com.ren.forexapi.api.models.schedule

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class ScheduleDTO(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lessons")
    val lessons: List<LessonsItemDTO>?
):Mappable