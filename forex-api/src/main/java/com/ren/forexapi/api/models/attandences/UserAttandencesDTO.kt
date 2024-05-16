package com.ren.forexapi.api.models.attandences

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class UserAttandencesDTO(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("total_attendances")
    val totalAttendances: Int = 0,
    @SerializedName("username")
    val username: String = ""
):Mappable