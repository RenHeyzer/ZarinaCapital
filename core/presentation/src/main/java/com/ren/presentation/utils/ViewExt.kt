package com.ren.presentation.utils

import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun EditText.trimmedText() = text.toString().trim()

fun TextInputLayout.isErrorEnable(isEnabled: Boolean, message: String? = null) {
    isErrorEnabled = isEnabled
    error = message
}