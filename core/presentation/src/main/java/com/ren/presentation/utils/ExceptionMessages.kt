package com.ren.presentation.utils

import android.content.Context
import com.ren.theme.R
import javax.inject.Inject

class ExceptionMessages @Inject constructor(private val context: Context) {

    val emptyFieldExceptionMessage =
        context.getString(R.string.empty_field_exception_message)
    val passwordMismatchExceptionMessage =
        context.getString(R.string.password_mismatch_exception_message)
    val authInvalidUserExceptionMessage =
        context.getString(R.string.auth_invalid_user_exception_message)
    val authInvalidCredentialsExceptionMessage =
        context.getString(R.string.auth_invalid_credentials_exception_message)
    val networkExceptionMessage = context.getString(R.string.network_exception_message)
    val tooManyRequestsExceptionMessage =
        context.getString(R.string.too_many_requests_exception_message)
    val authUserCollisionExceptionMessage =
        context.getString(R.string.auth_user_collision_exception_message)
    val authWeakPasswordExceptionMessage =
        context.getString(R.string.auth_weak_password_exception_message)
}