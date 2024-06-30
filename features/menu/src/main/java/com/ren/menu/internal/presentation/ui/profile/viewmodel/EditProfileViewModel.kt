package com.ren.menu.internal.presentation.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.domain.repositories.ProfileRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.ExceptionMessages
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
internal class  EditProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel() {

    private val _resultState = MutableLiveData<UIState<Unit>>()
    val resultState: LiveData<UIState<Unit>> = _resultState

    fun updateProfile(data: PUTProfile) {
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
