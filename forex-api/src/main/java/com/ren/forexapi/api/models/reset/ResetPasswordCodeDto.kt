package com.ren.forexapi.api.models.reset

import com.google.gson.annotations.SerializedName

data class ResetPasswordCodeDto(
    @SerializedName("code")
    val code: String,
)
