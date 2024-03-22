package com.ren.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ren.presentation.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected open fun <T> Flow<Result<T>>.collectFlowAsState(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch {
            this@collectFlowAsState.collect { result ->
                result.onFailure {
                    it.message?.let { message ->
                        state.value = UIState.Error(it, message)
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