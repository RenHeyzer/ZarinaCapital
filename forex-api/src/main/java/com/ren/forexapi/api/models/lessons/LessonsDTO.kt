package com.ren.forexapi.api.models.lessons

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class LessonsDTO(
    @SerializedName("start_time")
    val startTime: String = "",
    @SerializedName("homeworks")
    val homeworks: List<HomeworksItemDTO>?,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("end_time")
    val endTime: String = "",
    @SerializedName("id")
    val id: Int = 0
):Mappable