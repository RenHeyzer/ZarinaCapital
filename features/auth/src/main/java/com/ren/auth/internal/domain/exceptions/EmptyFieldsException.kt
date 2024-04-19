package com.ren.auth.internal.domain.exceptions

import com.ren.auth.internal.domain.entities.SignUpField

internal class EmptyFieldsException(
    val emptyFields: Map<SignUpField, Boolean>,
    val exceptionMessages: Map<SignUpField, String?>,
) : Exception()