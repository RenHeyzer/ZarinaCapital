package com.ren.forexapi.api.models

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class LoginResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("access_token")
    val accessToken: String,
): Mappable