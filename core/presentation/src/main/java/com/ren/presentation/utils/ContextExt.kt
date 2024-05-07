package com.ren.presentation.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

val Fragment.notNullContext
    get() = context ?: requireContext()

fun Context.getAttributeColor(attr: Int): Int {
    val typedValue = android.util.TypedValue()
    this.theme.resolveAttribute(attr, typedValue, true)
    return ContextCompat.getColor(this, typedValue.resourceId)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}