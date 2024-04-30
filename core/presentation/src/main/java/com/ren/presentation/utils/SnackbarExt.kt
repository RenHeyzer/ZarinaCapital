package com.ren.presentation.utils

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.makeSnackbar(text: String, duration: Int) {
    view?.let {
        Snackbar.make(it, text, duration).apply {

        }.show()
    }
}