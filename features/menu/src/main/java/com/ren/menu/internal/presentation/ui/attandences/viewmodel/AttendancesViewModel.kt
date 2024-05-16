package com.ren.menu.internal.presentation.ui.attandences.viewmodel

import com.ren.menu.internal.domain.entities.attandences.Attandences
import com.ren.menu.internal.domain.repositories.AttandencesRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AttendancesViewModel @Inject constructor(
    private val attendancesRepository: AttandencesRepository
) : BaseViewModel() {

    private val _attandencesState = MutableStateFlow<UIState<List<Attandences>>>(UIState.Loading)
    val attandencesState = _attandencesState.asStateFlow()

    init {
        attendancesRepository.fetchAttandences().collectFlowAsState(_attandencesState)
    }
}