package com.ren.menu.internal.domain.repositories

import com.ren.menu.internal.domain.entities.changepassword.ChangePassword

interface ChangePasswordRepository {

    suspend fun changePassword(changePassword: ChangePassword)

}