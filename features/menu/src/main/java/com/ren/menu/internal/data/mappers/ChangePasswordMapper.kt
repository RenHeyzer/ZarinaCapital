package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.changepassword.ChangePasswordDto
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import javax.inject.Inject

class ChangePasswordMapper @Inject constructor() : Mapper<ChangePasswordDto, ChangePassword> {



    override fun to(model: ChangePasswordDto) = with(model){
        ChangePassword(
            oldPassword = oldPassword,
            newPassword = newPassword
        )
    }

    override fun from(model: ChangePassword) = with(model){
        ChangePasswordDto(
            oldPassword = oldPassword,
            newPassword = newPassword
        )
    }


}
