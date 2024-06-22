package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.entities.LoginParams
import com.ren.auth.internal.domain.usecases.LoginUseCase
import com.ren.auth.internal.presentation.ui.fragments.SignInFragment
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
    private var isError = false

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val errorList = mutableListOf<String>()

            if (email.isEmpty()) {
                errorList.add(SignInFragment.EMAIL_KEY)
            } else if (!email.contains("@")) {
                isError = true
                _resultState.value = UIState.Error(
                    throwable = Throwable("Введите корректный email"),
                    errorList = listOf(SignInFragment.EMAIL_KEY),
                    message = "Введите корректный email"
                )
            }

            if (password.isEmpty()) {
                errorList.add(SignInFragment.PASSWORD_KEY)
            }

            if (errorList.isEmpty()) {
                if (!isError) {
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
                            errorList.add(SignInFragment.PASSWORD_KEY)
                            errorList.add(SignInFragment.EMAIL_KEY)
                            _resultState.value = UIState.Error(
                                throwable = Throwable("Неверный пароль или email"),
                                errorList = errorList,
                                message = "Неверный пароль или email"
                            )
                        }
                    )
                }
            } else {
                _resultState.value = UIState.Error(
                    throwable = Throwable("Заполните это поле"),
                    errorList = errorList,
                    message = "Заполните это поле"
                )
            }
        }
    }
}