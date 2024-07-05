package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.entities.LoginParams
import com.ren.auth.internal.domain.usecases.LoginUseCase
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.EMAIL_KEY
import com.ren.presentation.utils.ExceptionMessages
import com.ren.presentation.utils.PASSWORD_KEY
import com.ren.presentation.utils.UIState
import com.ren.presentation.utils.emailValid
import com.ren.presentation.utils.passwordValid
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
    private var isError = false

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val errorList = mutableMapOf<String, String>()
            val emailError = emailValid.isValid(email)
            val passwordError = passwordValid.isValid(password)

            if (emailError != null) {
                errorList.put(EMAIL_KEY, emailError)
            }
            if (passwordError != null) {
                errorList.put(PASSWORD_KEY, passwordError)
            }
            if (errorList.isNotEmpty()) {
                _resultState.value = UIState.Error(
                    errorList = errorList,
                )
            } else {
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
                        errorList.put(EMAIL_KEY, "Неверный пароль или email")
                        errorList.put(PASSWORD_KEY, "Неверный пароль или email")
                        _resultState.value = UIState.Error(
                            errorList = errorList,
                        )
                    }
                )
            }
        }
    }
}