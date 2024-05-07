package com.ren.auth.internal.domain.exceptions

import com.ren.auth.internal.domain.entities.AuthFields

internal class EmptyFieldsException(
    val emptyFields: Map<AuthFields, Boolean>,
    val exceptionMessages: Map<AuthFields, String?>,
) : Exception()