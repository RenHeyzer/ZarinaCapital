package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.domain.exceptions.PasswordMismatchException
import com.ren.auth.internal.domain.usecases.SignUpUseCase
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.ExceptionMessages
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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
            _resultState.value = UIState.Loading
            val params = with(exceptionMessages) {
                SignUpParams(
                    username = username to emptyUsernameFieldExceptionMessage,
                    email = email to emptyEmailFieldExceptionMessage,
                    phone = phone to emptyPhoneFieldExceptionMessage,
                    password = password to emptyPasswordFieldExceptionMessage,
                    confirmPassword = confirmPassword to emptyConfirmPasswordFieldExceptionMessage
                )
            }
            signUpUseCase(params).fold(
                onSuccess = {
                    _resultState.value = UIState.Success()
                },
                onFailure = { t ->
                    when (t) {
                        is EmptyFieldsException -> {
                            _resultState.value = UIState.Error(
                                throwable = t,
                                message = null
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
            )
        }
    }
}