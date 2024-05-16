package com.ren.forexapi.api.models.attandences

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class AttandencesDTO(
    @SerializedName("lesson")
    val lesson: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("user")
    val user: UserAttandencesDTO,
    @SerializedName("status")
    val status: Int = 0
):Mappable