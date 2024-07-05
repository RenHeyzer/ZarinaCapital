package com.ren.presentation.utils

sealed class UIState<out T> {

    data object Loading : UIState<Nothing>()

    data class Error(
        val throwable: Throwable? = null,
        val message: String? = null,
        val errorList: Map<String, String>? = null
    ) : UIState<Nothing>()

    data class Success<T>(val data: T? = null) : UIState<T>()
}