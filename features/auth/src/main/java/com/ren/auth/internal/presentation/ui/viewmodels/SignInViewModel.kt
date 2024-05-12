package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.entities.LoginParams
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.domain.usecases.LoginUseCase
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.ExceptionMessages
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val exceptionMessages: ExceptionMessages
) : BaseViewModel() {

    private val _resultState = MutableLiveData<UIState<Nothing>>()
    val resultState: LiveData<UIState<Nothing>> = _resultState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val params = with(exceptionMessages) {
                LoginParams(
                    email = email to emptyEmailFieldExceptionMessage,
                    password = password to emptyPasswordFieldExceptionMessage
                )
            }
            loginUseCase(params).fold(
                onSuccess = {
                    _resultState.value = UIState.Success()
                },
                onFailure = { exception ->
                    if (exception is EmptyFieldsException) {
                        _resultState.value = UIState.Error(
                            throwable = exception,
                            message = null
                        )
                    } else {
                        _resultState.value = UIState.Error(
                            throwable = exception,
                            message = exception.message ?: "Unknown error!"
                        )
                    }
                }
            )
        }
    }
}