package com.ren.menu.internal.presentation.ui.editpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ren.menu.internal.data.repositories.ChangePasswordRepository
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditPasswordViewModel @Inject constructor(
    private val changePasswordRepository: ChangePasswordRepository
) : BaseViewModel() {

    private val _changePasswordState = MutableLiveData<UIState<Unit>>()
    val changePasswordState: LiveData<UIState<Unit>> = _changePasswordState

    fun changePassword(oldPassword: String, newPassword: String) {
        viewModelScope.launch {
            _changePasswordState.value = UIState.Loading
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


