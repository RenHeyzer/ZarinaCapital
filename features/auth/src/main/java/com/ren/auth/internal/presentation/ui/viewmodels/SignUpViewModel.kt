package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.usecases.SignUpUseCase
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.CONFIRM_PASSWORD_KEY
import com.ren.presentation.utils.EMAIL_KEY
import com.ren.presentation.utils.ExceptionMessages
import com.ren.presentation.utils.NUMBER_KEY
import com.ren.presentation.utils.PASSWORD_KEY
import com.ren.presentation.utils.UIState
import com.ren.presentation.utils.USERNAME_KEY
import com.ren.presentation.utils.emailValid
import com.ren.presentation.utils.numberValid
import com.ren.presentation.utils.passwordValid
import com.ren.presentation.utils.usernameValid
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
    private var isHasError = false

    fun signUp(
        username: String,
        email: String,
        prefix: String,
        phone: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            val errorList = mutableMapOf<String, String>()
            val usernameError = usernameValid.isValid(username)
            val emailError = emailValid.isValid(email)
            val numberError = numberValid.isValid(phone)
            val passwordError = passwordValid.isValid(password)

            if (usernameError != null) {
                errorList.put(USERNAME_KEY, usernameError)
            }
            if (emailError != null) {
                errorList.put(EMAIL_KEY, emailError)
            }
            if (numberError != null) {
                errorList.put(NUMBER_KEY, numberError)
            }
            if (passwordError != null) {
                errorList.put(PASSWORD_KEY, passwordError)
            }
            if (password != confirmPassword) {
                errorList.put(PASSWORD_KEY, "Password missmatch")
                errorList.put(CONFIRM_PASSWORD_KEY, "Password missmatch")
            }
            if (errorList.isNotEmpty()) {
                _resultState.value = UIState.Error(
                    errorList = errorList
                )
            } else {
                _resultState.value = UIState.Loading
                val params = SignUpParams(
                    username = username to exceptionMessages.emptyUsernameFieldExceptionMessage,
                    email = email to exceptionMessages.emptyEmailFieldExceptionMessage,
                    prefix = prefix,
                    phone = phone to exceptionMessages.emptyPhoneFieldExceptionMessage,
                    password = password to exceptionMessages.emptyPasswordFieldExceptionMessage,
                    confirmPassword = confirmPassword to exceptionMessages.emptyConfirmPasswordFieldExceptionMessage
                )
                signUpUseCase(params).fold(
                    onSuccess = {
                        _resultState.value = UIState.Success()
                    },
                    onFailure = { t ->
                        _resultState.value = UIState.Error(
                            throwable = t,
                            message = "Произошла ошибка при регистрации или пользователь уже зарегестрирован"
                        )
                    }
                )
            }
        }
    }
}