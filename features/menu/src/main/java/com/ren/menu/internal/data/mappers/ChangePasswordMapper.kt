package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.changepassword.ChangePasswordDTO
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import javax.inject.Inject

internal class ChangePasswordMapper @Inject constructor() :
    Mapper<ChangePasswordDTO, ChangePassword> {
    override fun to(model: ChangePasswordDTO) = with(model) {
        ChangePassword(
            oldPassword = oldPassword,
            newPassword = newPassword
        )
    }

    override fun from(model: ChangePassword) = with(model) {
        ChangePasswordDTO(
            oldPassword = oldPassword,
            newPassword = newPassword
        )
    }

}