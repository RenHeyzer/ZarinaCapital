package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.forexapi.api.models.auth.LoginResponse
import com.ren.forexapi.api.models.auth.UserDTO
import com.ren.forexapi.api.models.changepassword.ChangePasswordDTO
import com.ren.forexapi.api.service.auth.AuthApiService
import com.ren.forexapi.api.service.changepassword.ChangePasswordApiService
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import com.ren.menu.internal.domain.repositories.ChangePasswordRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangePasswordDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val changeApiService: ChangePasswordApiService,
    private val userMapper: Mapper<ChangePasswordDTO, ChangePassword>
) : ChangePasswordRepository {

    override suspend fun changePassword(changePassword: ChangePassword) {
        withContext(appDispatchers.io) {
            changeApiService.changePassword(userMapper.from(changePassword))
        }
    }
}