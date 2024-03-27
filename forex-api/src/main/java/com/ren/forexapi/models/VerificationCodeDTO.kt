package com.ren.forexapi.models

import com.google.gson.annotations.SerializedName

data class VerificationCodeDTO(
    @SerializedName("code")
    val code: Int
)