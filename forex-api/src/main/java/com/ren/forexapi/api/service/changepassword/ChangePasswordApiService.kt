package com.ren.forexapi.api.service.changepassword

import com.ren.forexapi.api.models.changepassword.ChangePasswordDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ChangePasswordApiService {
    @POST("change_password/")
    suspend fun changePassword(
        @Body changePasswordDto: ChangePasswordDto
    ): ChangePasswordDto
}