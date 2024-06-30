package com.ren.forexapi.api.models.reset

import com.google.gson.annotations.SerializedName

data class ResetNewPasswordDto(
    @SerializedName("password")
    val password: String,
)
