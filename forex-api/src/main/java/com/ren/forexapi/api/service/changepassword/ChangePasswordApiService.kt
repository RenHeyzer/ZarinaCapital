package com.ren.forexapi.api.service.changepassword

import com.ren.forexapi.api.models.auth.UserDTO
import com.ren.forexapi.api.models.changepassword.ChangePasswordDTO
import retrofit2.http.Body
import retrofit2.http.POST

private const val END_POINT = "change_password/"

interface ChangePasswordApiService {

    @POST(END_POINT)
    suspend fun changePassword(
        @Body changePassword: ChangePasswordDTO
    ): ChangePasswordDTO
}
