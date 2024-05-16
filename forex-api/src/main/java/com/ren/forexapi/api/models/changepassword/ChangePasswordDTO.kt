package com.ren.forexapi.api.models.changepassword

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class ChangePasswordDTO(
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("new_password")
    val newPassword: String
) : Mappable