package com.ren.menu.internal.presentation.ui.editpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.domain.repositories.ChangePasswordRepository
import com.ren.menu.internal.domain.repositories.ProfileRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPasswordViewModel @Inject constructor(
    private val changePasswordRepository: ChangePasswordRepository,
) : BaseViewModel() {

    private val _resultState = MutableLiveData<UIState<Nothing>>()
    val resultState: LiveData<UIState<Nothing>> = _resultState

    fun changePasswordProfile(data: ChangePassword) {
        viewModelScope.launch {
            changePasswordRepository.changePassword(
                data
            )
        }
    }
}