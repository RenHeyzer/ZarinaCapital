package com.ren.menu.internal.presentation.ui.editpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.menu.internal.data.repositories.ChangePasswordRepository
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.CONFIRM_PASSWORD_KEY
import com.ren.presentation.utils.OLD_PASSWORD
import com.ren.presentation.utils.PASSWORD_KEY
import com.ren.presentation.utils.UIState
import com.ren.presentation.utils.passwordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditPasswordViewModel @Inject constructor(
    private val changePasswordRepository: ChangePasswordRepository
) : BaseViewModel() {

    private val _changePasswordState = MutableLiveData<UIState<Unit>>()
    val changePasswordState: LiveData<UIState<Unit>> = _changePasswordState

    fun changePassword(oldPassword: String, newPassword: String, confirmPassword: String) {
        viewModelScope.launch {
            _changePasswordState.value = UIState.Loading
            val errorList = mutableMapOf<String, String>()

            val oldPasswordError = passwordValid.isValid(oldPassword)
            val newPasswordError = passwordValid.isValid(newPassword)

            if (oldPasswordError != null) {
                errorList.put(OLD_PASSWORD, oldPasswordError)
            }
            if (newPasswordError != null) {
                errorList.put(OLD_PASSWORD, newPasswordError)
            }
            if (newPasswordError != confirmPassword) {
                errorList.put(PASSWORD_KEY, "Password missmatch")
                errorList.put(CONFIRM_PASSWORD_KEY, "Password missmatch")
            }

            if (errorList.isNotEmpty()) {
                _changePasswordState.value = UIState.Error(errorList = errorList)
            } else {
                try {
                    val result = changePasswordRepository.changePassword(
                        ChangePassword(
                            oldPassword = oldPassword,
                            newPassword = newPassword
                        )
                    )
                    _changePasswordState.value = result
                } catch (e: Exception) {
                    _changePasswordState.value = UIState.Error(e)
                }
            }
        }
    }
}


