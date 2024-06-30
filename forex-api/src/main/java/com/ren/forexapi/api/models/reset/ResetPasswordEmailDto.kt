package com.ren.forexapi.api.models.reset

import com.google.gson.annotations.SerializedName

data class ResetPasswordEmailDto(
    @SerializedName("email")
    val email: String,
)