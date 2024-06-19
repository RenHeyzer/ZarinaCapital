package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.entities.SignUpParams
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.domain.exceptions.PasswordMismatchException
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
            if (username.isEmpty()) {
                errorList.add(SignUpFragment.FULL_NAME_KEY)
            }
            if (email.isEmpty()) {
                errorList.add(SignUpFragment.EMAIL_KEY)
            }
            if (phone.isEmpty()) {
                errorList.add(SignUpFragment.PHONE_KEY)
            }
            if (password.isEmpty()) {
                errorList.add(SignUpFragment.PASSWORD_KEY)
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
            }else {
                if (confirmPassword == password) {
                    _resultState.value = UIState.Loading
                    val params = with(exceptionMessages) {
                        SignUpParams(
                            username = username to emptyUsernameFieldExceptionMessage,
                            email = email to emptyEmailFieldExceptionMessage,
                            prefix = prefix,
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

                        }
                    )
                }else {
                    _resultState.value = UIState.Error(
                        throwable = Throwable("Пароли не совподают"),
                        errorList = listOf(
                            SignUpFragment.PASSWORD_KEY,
                            SignUpFragment.CONFIRM_PASSWORD_KEY,
                        ),
                        message = "Пароли не совподают"
                    )
                }
            }
        }
    }
}