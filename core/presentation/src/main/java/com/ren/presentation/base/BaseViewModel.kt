package com.ren.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ren.common.Either
import com.ren.presentation.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open fun <T> Flow<Either<Throwable, T>>.collectFlowAsState(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch {
            this@collectFlowAsState.collect {
                when (it) {
                    is Either.Left -> {
                        it.left?.let { t ->
                            val message = t.message ?: "Unknown error!"
                            state.value = UIState.Error(t, message)
                        }
                    }

                    is Either.Right -> {
                        it.right?.let { data ->
                            state.value = UIState.Success(data)
                        }
                    }
                }
            }
        }
    }

                result.onSuccess {
                    it?.let { data ->
                        state.value = UIState.Success(data)
                    }
                }
            }
        }
    }
}