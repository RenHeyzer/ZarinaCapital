package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.geeks.di.scopes.ScreenScope
import com.ren.auth.entities.SignUpParams
import com.ren.auth.exceptions.EmptyFieldException
import com.ren.auth.exceptions.PasswordMismatchException
import com.ren.auth.usecases.SignUpUseCase
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.ExceptionMessages
import com.ren.presentation.utils.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
internal class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val exceptionMessages: ExceptionMessages
) : BaseViewModel() {

    private val _resultState = MutableLiveData<UIState<Nothing>>()
    val resultState: LiveData<UIState<Nothing>> = _resultState

    fun signUp(
        username: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                val params = SignUpParams(
                    username = username,
                    email = email,
                    phone = phone,
                    password = password,
                    confirmPassword = confirmPassword
                )
                signUpUseCase(params)
            }.onSuccess {
                _resultState.value = UIState.Success()
            }.onFailure { t ->
                when (t) {
                    is EmptyFieldException -> {
                        _resultState.value = UIState.Error(
                            throwable = t,
                            message = exceptionMessages.emptyFieldExceptionMessage
                        )
                    }

                    is PasswordMismatchException -> {
                        _resultState.value = UIState.Error(
                            throwable = t,
                            message = exceptionMessages.passwordMismatchExceptionMessage
                        )
                    }

                    else -> {
                        _resultState.value = UIState.Error(
                            throwable = t,
                            message = t.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }
}