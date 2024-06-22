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
    private var isError = false

    fun resetPassword(code: String, newPassword: String, confirmNewPassword: String) {
        viewModelScope.launch {
            val errorList = mutableListOf<String>()

            if (newPassword.isEmpty()) {
                errorList.add(ResetPasswordFragment.PASSWORD_KEY)
            } else if (newPassword.length < 8) {
                isError = true
                _resultState.value = UIState.Error(
                    throwable = Throwable("Пароль должен состоять больше 8-х символов"),
                    errorList = listOf(ResetPasswordFragment.PASSWORD_KEY),
                    message = "Пароль должен состоять больше 8-х символов"
                )
            }
            if (confirmNewPassword.isEmpty()) {
                errorList.add(ResetPasswordFragment.CONFIRM_PASSWORD_KEY)
            }
            if (errorList.isEmpty() && newPassword == confirmNewPassword) {
                if (!isError) {
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
                } else {
                    _resultState.value = UIState.Error(
                        throwable = Throwable("Заполните это поле"),
                        errorList = errorList,
                        message = "Заполните это поле"
                    )
                }
            } else {
                _resultState.value = UIState.Error(
                    throwable = Throwable("Пороли не совподают"),
                    errorList = listOf(
                        ResetPasswordFragment.PASSWORD_KEY,
                        ResetPasswordFragment.CONFIRM_PASSWORD_KEY
                    ),
                    message = "Пороли не совподают"
                )
            }
        }
    }
}