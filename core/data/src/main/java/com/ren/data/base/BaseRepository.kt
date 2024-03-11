package com.ren.data.base

import com.ren.common.AppDispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository(private val appDispatchers: AppDispatchers) {

    protected fun <F, T> doRequest(
        map: ((it: F) -> T)? = null,
        request: suspend () -> F
    ) = flow {
        map?.let { invoke ->
            emit(
                Result.success(
                    invoke(request())
                )
            )
        }
    }.flowOn(appDispatchers.io).catch {
        emit(Result.failure(it))
    }
}