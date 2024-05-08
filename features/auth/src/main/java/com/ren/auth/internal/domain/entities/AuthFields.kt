package com.ren.auth.internal.domain.entities

internal sealed interface AuthFields {

    enum class SignUpField : AuthFields {
        USERNAME,
        EMAIL,
        PHONE,
        PASSWORD,
        CONFIRM_PASSWORD
    }

    enum class LoginField : AuthFields {
        EMAIL,
        PASSWORD
    }
}