package com.ren.menu.internal.domain.entities.changepassword

import com.ren.common.Mappable

data class ChangePassword(
    val oldPassword: String,
    val newPassword: String
) : Mappable