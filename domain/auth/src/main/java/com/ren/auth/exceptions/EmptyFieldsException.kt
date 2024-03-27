package com.ren.auth.exceptions

import com.ren.auth.entities.SignUpField

class EmptyFieldsException(
    val emptyFields: Map<SignUpField, Boolean>,
    val exceptionMessages: Map<SignUpField, String?>,
) : Exception()