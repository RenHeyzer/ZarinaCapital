package com.ren.menu.internal.presentation.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.domain.repositories.ProfileRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.EMAIL_KEY
import com.ren.presentation.utils.NUMBER_KEY
import com.ren.presentation.utils.UIState
import com.ren.presentation.utils.USERNAME_KEY
import com.ren.presentation.utils.emailValid
import com.ren.presentation.utils.numberValid
import com.ren.presentation.utils.usernameValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel() {

    private val _resultState = MutableLiveData<UIState<Unit>>()
    val resultState: LiveData<UIState<Unit>> = _resultState

    fun updateProfile(data: PUTProfile) {
        val errorList = mutableMapOf<String, String>()

        val usernameError = usernameValid.isValid(data.username)
        val numberError = numberValid.isValid(data.phone)
        val emailError = emailValid.isValid(data.email)

        if (usernameError != null) {
            errorList.put(USERNAME_KEY, usernameError)
        }
        if (numberError != null) {
            errorList.put(NUMBER_KEY, numberError)
        }
        if (emailError != null) {
            errorList.put(EMAIL_KEY, emailError)
        }
        if (errorList.isNotEmpty()) {
            _resultState.value = UIState.Error(
                errorList = errorList
            )
        }else {
            viewModelScope.launch {
                _resultState.value = UIState.Loading
                try {
                    profileRepository.putProfile(data)
                    _resultState.value = UIState.Success(Unit)
                } catch (e: Exception) {
                    _resultState.value = UIState.Error(e)
                }
            }
        }
    }
}
