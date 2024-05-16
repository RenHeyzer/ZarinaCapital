package com.ren.forexapi.api.models.lessons

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class HomeworksItemDTO(
    @SerializedName("homework")
    val homework: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("deadline")
    val deadline: String = ""
):Mappable