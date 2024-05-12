package com.ren.forexapi.api.models.auth

import com.google.gson.annotations.SerializedName

data class VerificationCodeDTO(
    @SerializedName("otp")
    val otp: Int
)