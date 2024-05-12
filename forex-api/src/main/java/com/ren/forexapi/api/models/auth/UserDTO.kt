package com.ren.forexapi.api.models.auth

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class UserDTO(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("password")
    val password: String
): Mappable