package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.repositories.ResetPasswordRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val resetPasswordRepository: ResetPasswordRepository) :
    BaseViewModel() {

    private var _resultState = MutableLiveData<UIState<Nothing>>()
    val resultState: LiveData<UIState<Nothing>> = _resultState


    fun resetPassword(code: String, newPassword: String) {
        viewModelScope.launch {
            runCatching {
                resetPasswordRepository.resetPasswordWithCode(code = code, newPassword = newPassword)
            }.fold(
                onFailure = { error ->
                    _resultState.value = UIState.Error(error)
                },
                onSuccess = {
                    _resultState.value = UIState.Success()
                },
            )
        }
    }
}