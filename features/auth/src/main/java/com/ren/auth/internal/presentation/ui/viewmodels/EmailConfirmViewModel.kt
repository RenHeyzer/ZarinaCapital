package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.repositories.ResetPasswordRepository
import com.ren.auth.internal.presentation.ui.fragments.EmailConfirmFragment
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailConfirmViewModel @Inject constructor(
    private val resetPasswordRepository: ResetPasswordRepository
) : BaseViewModel() {

    private var _resultState = MutableLiveData<UIState<Nothing>>()
    val resultState: LiveData<UIState<Nothing>> = _resultState
    private var isError = false

    fun checkEmail(email: String) {
        viewModelScope.launch {
            if (email.isEmpty()) {
                isError = true
                _resultState.value = UIState.Error(
                    throwable = Throwable("Заполните это поле"),
                    errorList = listOf(EmailConfirmFragment.EMAIL_KEY),
                    message = "Заполните это поле"
                )
            } else if (!email.contains("@")) {
                isError = true
                _resultState.value = UIState.Error(
                    throwable = Throwable("Введите корректный email"),
                    errorList = listOf(EmailConfirmFragment.EMAIL_KEY),
                    message = "Введите корректный email"
                )
            }
            if (!isError) {
                runCatching {
                    resetPasswordRepository.checkEmail(email)
                }.fold(
                    onSuccess = {
                        _resultState.value = UIState.Success()
                    },
                    onFailure = { throwable ->
                        _resultState.value =
                            UIState.Error(throwable = throwable, message = throwable.message)
                    }
                )
            }
        }
    }
}