package com.ren.menu.internal.domain.repositories

import com.ren.forexapi.api.models.changepassword.ChangePasswordDto
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword

interface ChangePasswordRepository {
    suspend fun changePassword(data: ChangePassword): ChangePassword
}
