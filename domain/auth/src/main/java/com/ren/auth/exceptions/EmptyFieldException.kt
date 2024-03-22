package com.ren.auth.exceptions

import com.ren.auth.entities.SignUpField

class EmptyFieldException(val field: SignUpField) : Exception()