package com.ren.auth.internal.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.auth.internal.domain.repositories.ResetPasswordRepository
import com.ren.auth.internal.presentation.ui.fragments.EmailConfirmFragment
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.EMAIL_KEY
import com.ren.presentation.utils.UIState
import com.ren.presentation.utils.emailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailConfirmViewModel @Inject constructor(
    private val resetPasswordRepository: ResetPasswordRepository
) : BaseViewModel() {

    private var _resultState = MutableLiveData<UIState<Nothing>>()
    val resultState: LiveData<UIState<Nothing>> = _resultState

    fun checkEmail(email: String) {
        val emailError = emailValid.isValid(email)
        if (emailError != null) {
            _resultState.value = UIState.Error(
                errorList = mapOf(EMAIL_KEY to emailError)
            )
        }
        viewModelScope.launch {
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