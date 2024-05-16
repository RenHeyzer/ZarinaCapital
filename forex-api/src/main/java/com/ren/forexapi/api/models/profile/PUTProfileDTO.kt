package com.ren.forexapi.api.models.profile

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class PUTProfileDTO(
    @SerializedName("username")
    val username: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String
):Mappable