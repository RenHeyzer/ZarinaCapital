package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.usecases.ConfirmEmailUseCase
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ConfirmEmailViewModel @Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCase
) : BaseViewModel() {

    private val _resultState = MutableLiveData<UIState<Nothing>>()
    val resultState: LiveData<UIState<Nothing>> = _resultState

    fun confirmEmail(code: Int) {
        viewModelScope.launch {
            _resultState.value = UIState.Loading
            confirmEmailUseCase(code).fold(
                onSuccess = {
                    _resultState.value = UIState.Success()
                },
                onFailure = {
                    _resultState.value = UIState.Error(it)
                }
            )
        }
    }
}