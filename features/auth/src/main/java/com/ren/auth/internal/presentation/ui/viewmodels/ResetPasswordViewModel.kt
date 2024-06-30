package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.repositories.ResetPasswordRepository
import com.ren.auth.internal.presentation.ui.fragments.ResetPasswordFragment
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

    private var _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private var _confirmPasswordError = MutableLiveData<String?>()
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError

    fun resetPassword(code: String, newPassword: String, confirmNewPassword: String) {
        viewModelScope.launch {
            var isValid = true

            if (newPassword.isEmpty()) {
                _passwordError.value = "Пароль не может быть пустым"
                isValid = false
            } else if (newPassword.length < 8) {
                _passwordError.value = "Пароль должен состоять больше 8-х символов"
                isValid = false
            } else {
                _passwordError.value = null
            }

            if (confirmNewPassword.isEmpty()) {
                _confirmPasswordError.value = "Подтверждение пароля не может быть пустым"
                isValid = false
            } else if (newPassword != confirmNewPassword) {
                _confirmPasswordError.value = "Пароли не совпадают"
                isValid = false
            } else {
                _confirmPasswordError.value = null
            }

            if (isValid) {
                runCatching {
                    resetPasswordRepository.resetPasswordWithCode(
                        code = code,
                        newPassword = newPassword
                    )
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
}
