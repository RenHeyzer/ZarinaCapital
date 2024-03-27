package com.ren.presentation.utils

import android.content.Context
import com.ren.theme.R
import javax.inject.Inject

class ExceptionMessages @Inject constructor(context: Context) {

    val emptyUsernameFieldExceptionMessage =
        context.getString(R.string.empty_username_field_exception_message)
    val emptyPhoneFieldExceptionMessage =
        context.getString(R.string.empty_phone_field_exception_message)
    val emptyEmailFieldExceptionMessage =
        context.getString(R.string.empty_email_field_exception_message)
    val emptyPasswordFieldExceptionMessage =
        context.getString(R.string.empty_password_field_exception_message)
    val emptyConfirmPasswordFieldExceptionMessage =
        context.getString(R.string.empty_confirm_password_field_exception_message)
    val requiredFieldExceptionMessage =
        context.getString(R.string.required_field_exception_message)
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