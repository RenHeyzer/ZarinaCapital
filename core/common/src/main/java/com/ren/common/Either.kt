package com.ren.common

sealed class Either<out L, out R> {
    data class Left<out L>(val t: Throwable? = null): Either<L, Nothing>()
    data class Right<out R>(val data: R? = null): Either<Nothing, R>()
}