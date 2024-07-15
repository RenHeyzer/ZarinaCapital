package com.ren.menu.internal.presentation.ui.forex_course.lessons

import com.ren.menu.internal.domain.entities.schedule.Schedule
import com.ren.menu.internal.domain.repositories.ScheduleRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : BaseViewModel() {

    private val _scheduleState = MutableStateFlow<UIState<List<Schedule>>>(UIState.Loading)
    val scheduleState = _scheduleState.asStateFlow()

    init {
        scheduleRepository.fetchSchedule().collectFlowAsState(_scheduleState)
    }
}