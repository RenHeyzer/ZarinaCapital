package com.ren.presentation.utils

sealed class UIState<T> {

    class Loading<T> : UIState<T>()

    class Error<T>(val throwable: Throwable, val message: String? = null) : UIState<T>()

    class Success<T>(val data: T? = null) : UIState<T>()
}