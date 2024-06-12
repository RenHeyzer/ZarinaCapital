package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.repositories.ResetPasswordRepository
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordCodeViewModel
@Inject constructor(private val resetPasswordRepository: ResetPasswordRepository) : ViewModel() {

    private var _resultState = MutableLiveData<UIState<String>>()
    val resultState: LiveData<UIState<String>> = _resultState

    fun checkCode(code: Int) {
        viewModelScope.launch {
            runCatching {
                resetPasswordRepository.getCode(code.toString())
            }.fold(
                onFailure = { error ->
                    _resultState.value = UIState.Error(error)
                },
                onSuccess = {
                    _resultState.value = UIState.Success(code.toString())
                }
            )
        }
    }
}