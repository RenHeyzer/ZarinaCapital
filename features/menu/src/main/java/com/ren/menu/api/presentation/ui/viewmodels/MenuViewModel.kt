package com.ren.menu.api.presentation.ui.viewmodels

import com.ren.menu.internal.domain.entities.profile.Profile
import com.ren.menu.internal.domain.repositories.ProfileRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : BaseViewModel() {

    private val _profileState = MutableStateFlow<UIState<Profile>>(UIState.Loading)
    val profileState = _profileState.asStateFlow()

    init {
        profileRepository.fetchProfile().collectFlowAsState(_profileState)
    }
    fun updateProfile(){
        profileRepository.fetchProfile().collectFlowAsState(_profileState)
    }
}