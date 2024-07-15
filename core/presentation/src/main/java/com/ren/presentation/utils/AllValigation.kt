package com.ren.presentation.utils

import com.ren.presentation.base.BaseValidation

val emailValid = object : BaseValidation {
    override fun isValid(text: String): String? {
        return if (text.isEmpty()) {
            "Fill the field"
        } else if (!text.contains("@") && !text.contains(".com")) {
            "Email is not valid"
        } else {
            null
        }
    }
}

val passwordValid = object : BaseValidation {
    override fun isValid(text: String): String? {
        return if (text.isEmpty()) {
            "Fill the field"
        } else if (text.length < 8) {
            "Password must be at least 8 characters"
        } else {
            null
        }
    }
}

val usernameValid = object : BaseValidation {
    override fun isValid(text: String): String? {
        return if (text.isEmpty()) {
            "Fill the field"
        } else if (text.length < 3) {
            "Username must be at least 3 characters"
        } else {
            null
        }
    }
}

val numberValid = object : BaseValidation {
    override fun isValid(text: String): String? {
        return if (text.isEmpty()) {
            "Fill the field"
        } else if (text.length < 9) {
            "Number must be at least 9 characters"
        } else {
            null
        }
    }
}

const val EMAIL_KEY = "email"
const val PASSWORD_KEY = "password"
const val OLD_PASSWORD = "oldPassword"
const val CONFIRM_PASSWORD_KEY = "confirmPassword"
const val USERNAME_KEY = "username"
const val NUMBER_KEY = "number"