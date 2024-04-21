package com.ren.data.base

import com.ren.common.AppDispatchers
import com.ren.common.Either
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository(private val appDispatchers: AppDispatchers) {

    protected fun <F, T> doRequest(
        request: suspend () -> F,
        map: ((it: F) -> T)? = null
    ) = flow<Either<Throwable, T>> {
        map?.let { invoke ->
            emit(
                Either.Right(invoke(request()))
            )
        }
    }.flowOn(appDispatchers.io).catch {
        emit(Either.Left(it))
    }
}