package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.usecases.SignUpUseCase
import com.ren.auth.internal.presentation.ui.fragments.SignUpFragment
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
            val errorList = mutableListOf<String>()

            errorList.clear()

            if (username.isEmpty()) {
                errorList.add(SignUpFragment.FULL_NAME_KEY)
            } else if (username.length < 3) {
                setError(
                    "Имя пользователя должно состоять больше 3-х символов",
                    listOf(SignUpFragment.FULL_NAME_KEY)
                )
            }

            if (email.isEmpty()) {
                errorList.add(SignUpFragment.EMAIL_KEY)
            } else if (!email.contains("@")) {
                setError("Введите корректный email", listOf(SignUpFragment.EMAIL_KEY))
            }

            if (phone.isEmpty()) {
                errorList.add(SignUpFragment.PHONE_KEY)
            } else if (phone.length < 9) {
                setError("Введите корректный номер телефона", listOf(SignUpFragment.PHONE_KEY))
            }

            if (password.isEmpty()) {
                errorList.add(SignUpFragment.PASSWORD_KEY)
            } else if (password.length < 8) {
                setError(
                    "Пароль должен состоять больше 8-х символов",
                    listOf(SignUpFragment.PASSWORD_KEY)
                )
            }

            if (confirmPassword.isEmpty()) {
                errorList.add(SignUpFragment.CONFIRM_PASSWORD_KEY)
            }

            if (errorList.isNotEmpty()) {
                _resultState.value = UIState.Error(
                    throwable = Throwable("Заполните это поле"),
                    errorList = errorList,
                    message = "Заполните это поле"
                )
            } else if (confirmPassword != password) {
                _resultState.value = UIState.Error(
                    throwable = Throwable("Пароли не совпадают"),
                    errorList = listOf(
                        SignUpFragment.PASSWORD_KEY,
                        SignUpFragment.CONFIRM_PASSWORD_KEY,
                    ),
                    message = "Пароли не совпадают"
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

    private fun setError(message: String, errorsList: List<String>) {
        isHasError = true
        _resultState.value = UIState.Error(
            throwable = Throwable(message),
            errorList = errorsList,
            message = message
        )
    }
}